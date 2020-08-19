package com.emreakcadag.architecturecomponents_hilt.base.network

import com.emreakcadag.architecturecomponents_hilt.base.extension.dialogBoxChecker
import com.emreakcadag.architecturecomponents_hilt.base.extension.logDebug
import com.emreakcadag.architecturecomponents_hilt.base.network.util.DialogBoxHandler
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Emre Akçadağ on 17.08.2020
 */
open class BaseRemoteDataSource {

    @Inject
    lateinit var dialogBoxHandler: DialogBoxHandler

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

        // Gelen response'e bakar [DialogBox] varsa DialogBoxHandler ile DialogBox gösterilir.
        if (data.dialogBoxChecker()) {
            dialogBoxHandler.showDialogBox((data as? BaseResponse?)?.dialogBox)
        }
    }

    private suspend fun <T : Any?> safeApiResult(
        call: suspend () -> Response<T?>,
        errorMessage: String?
    ): BaseResult<T?> {
        val response = call()
        if (response.isSuccessful) return BaseResult.Success(response.body())

        return BaseResult.Error(IOException("Hataaa - $errorMessage"))
    }
}