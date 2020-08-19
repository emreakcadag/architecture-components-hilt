package com.emreakcadag.architecturecomponents_hilt.feature.main.data

import com.emreakcadag.architecturecomponents_hilt.feature.main.data.request.MainRequest
import com.emreakcadag.architecturecomponents_hilt.feature.main.data.response.MainResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Emre Akçadağ on 17.08.2020
 */
interface MainApiService {

    @POST("main2")
    suspend fun getMainData(@Body mainRequest: MainRequest): Response<MainResponse?>
}