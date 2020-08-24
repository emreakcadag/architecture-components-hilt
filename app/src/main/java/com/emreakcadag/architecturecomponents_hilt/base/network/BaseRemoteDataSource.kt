package com.emreakcadag.architecturecomponents_hilt.base.network

import com.emreakcadag.architecturecomponents_hilt.base.extension.logDebug
import com.emreakcadag.architecturecomponents_hilt.base.extension.tryCatch
import retrofit2.Response
import java.io.IOException

/**
 * Created by Emre Akçadağ on 17.08.2020
 */
open class BaseRemoteDataSource {

    suspend fun <T : Any?> safeApiCall(call: suspend () -> Response<T?>, successHandler: (T?) -> Unit, errorMessage: String?) {

        val result: BaseResult<T?> = safeApiResult(call, errorMessage)
        var data: T? = null

        when (result) {
            is BaseResult.Success -> {
                data = result.data
                successHandler(data)
            }
            is BaseResult.Error -> logDebug("$errorMessage & Exception - ${result.exception}")
        }
    }

    private suspend fun <T : Any?> safeApiResult(
        call: suspend () -> Response<T?>,
        errorMessage: String?
    ): BaseResult<T?> {
        val response = tryCatch({ call() })
        if (response?.isSuccessful == true) return BaseResult.Success(response.body())

        return BaseResult.Error(IOException("Hataaa - $errorMessage"))
    }
}