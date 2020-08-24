package com.emreakcadag.architecturecomponents_hilt.base.network

import com.emreakcadag.architecturecomponents_hilt.base.extension.dialogBoxChecker
import com.emreakcadag.architecturecomponents_hilt.base.extension.logDebug
import com.emreakcadag.architecturecomponents_hilt.base.extension.tryCatch
import com.emreakcadag.architecturecomponents_hilt.base.network.util.DialogBoxHandler
import com.google.gson.Gson
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import java.io.EOFException
import java.nio.charset.Charset
import javax.inject.Inject

/**
 * Created by Emre Akcadag on 8.08.2020
 */
class BaseNetworkInterceptor @Inject constructor(
    private val gson: Gson,
    private val dialogBoxHandler: DialogBoxHandler
) : Interceptor {

    private val requestUrlList = mutableListOf<String>()

    companion object {
        private const val LOG_TAG = "APP_NETWORK"
        private const val REQUEST_URL = "REQUEST_URL"
        private const val REQUEST_BODY = "REQUEST_BODY"
        private const val RESPONSE_BODY = "RESPONSE_BODY"
        private const val REQUEST_FAILED = "REQUEST_FAILED"
        private const val END_OF_REQUEST = "END_OF_REQUEST"
        private const val LOG_DIVIDER = " - - - "

        private val UTF8 = Charset.forName("UTF-8")
    }

    @Volatile
    private var level = Level.NONE

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        return when (level) {
            Level.NONE -> chain.proceed(request)
            Level.BODY -> {
                val requestBody = request.body

                setRequestUrlList(request.url)

                /**
                 * Logs request method and url
                 */
                log(REQUEST_URL, "${request.method} ${request.url}", getRequestNumber(request.url))

                val requestBuffer = Buffer()
                requestBody?.writeTo(requestBuffer)

                val requestCharset = requestBody?.contentType()?.run {
                    charset(UTF8)
                } ?: UTF8

                if (isPlaintext(requestBuffer)) {
                    /**
                     * Logs request Body
                     */
                    log(REQUEST_BODY, requestBuffer.readString(requestCharset), getRequestNumber(request.url))
                }

                val response = tryCatch({
                    chain.proceed(request)
                }, {
                    log(REQUEST_FAILED, it.toString(), getRequestNumber(request.url))
                })

                val responseBody = response?.body

                val source = responseBody?.source()
                source?.request(Long.MAX_VALUE)

                val responseCharset = responseBody?.contentType()?.charset(UTF8) ?: UTF8

                if (responseBody?.contentLength() != 0L) {
                    /**
                     * Logs response body
                     */
                    val resBody = source?.buffer?.clone()?.readString(responseCharset)
                    log(RESPONSE_BODY, resBody, getRequestNumber(response?.request?.url))

                    // todo emreakcadag prod implementation
                    showDialogIfExist(resBody)
                }

                /**
                 * End of request
                 */
                log(END_OF_REQUEST, LOG_DIVIDER, getRequestNumber(response?.request?.url))

                response ?: chain.proceed(request)
            }
        }
    }

    private fun showDialogIfExist(resBody: String?) {
        tryCatch({
            val baseResponse = gson.fromJson(resBody, BaseResponse::class.java)

            if (baseResponse.dialogBoxChecker()) {
                dialogBoxHandler.showDialogBox((baseResponse)?.dialogBox)
            }
        })
    }

    /**
     * Logs as info type
     */
    private fun log(detailedTag: String? = null, message: String? = null, requestNumber: Int? = -1) {
        val tag = if (detailedTag != null) "${LOG_TAG}_${requestNumber}_$detailedTag" else "${LOG_TAG}_${requestNumber}"
        message?.let { logDebug(it, tag) }
    }

    private fun setRequestUrlList(url: HttpUrl?) = url?.run {
        requestUrlList.add(toString())
    }

    /**
     * @returns the requestNumber of given [url]
     */
    private fun getRequestNumber(url: HttpUrl?) = url?.run {
        requestUrlList.lastIndexOf(toString())
    }

    private fun isPlaintext(buffer: Buffer?): Boolean {
        return try {
            val prefix = Buffer()
            val byteCount = if (buffer?.size ?: 0 < 64) buffer?.size ?: 0 else 64
            buffer?.copyTo(prefix, 0, byteCount)
            for (i in 0..15) {
                if (prefix.exhausted()) {
                    break
                }
                val codePoint = prefix.readUtf8CodePoint()
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false
                }
            }
            true
        } catch (e: EOFException) {
            false
        }
    }

    /**
     * Sets level.
     */
    fun setLevel(level: Level?) {
        level?.let {
            this.level = it
        } ?: throw NullPointerException("level == null. Use Level.NONE instead.")
    }

    enum class Level {
        /** No logs */
        NONE,

        /** Custom settings, check intercept function */
        BODY;
    }
}
