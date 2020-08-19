package com.emreakcadag.architecturecomponents_hilt.feature.main.data.source

import com.emreakcadag.architecturecomponents_hilt.base.network.BaseRemoteDataSource
import com.emreakcadag.architecturecomponents_hilt.feature.main.data.MainApiService
import com.emreakcadag.architecturecomponents_hilt.feature.main.data.request.MainRequest
import com.emreakcadag.architecturecomponents_hilt.feature.main.data.response.MainResponse
import javax.inject.Inject

/**
 * Created by Emre Akçadağ on 17.08.2020
 */
class MainRemoteDataSource @Inject constructor(private val service: MainApiService) : BaseRemoteDataSource() {

    suspend fun getMainData(request: MainRequest, handler: (MainResponse?) -> Unit) = safeApiCall(
        call = { service.getMainData(request) },
        successHandler = handler,
        errorMessage = "Error Fetching Main Response"
    )
}