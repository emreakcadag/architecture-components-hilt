package com.emreakcadag.architecturecomponents_hilt.base.network

import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by Emre Akçadağ on 17.08.2020
 */
class BaseRetrofitService @Inject constructor(val retrofit: Retrofit) {

    inline fun <reified T> create(apiClass: Class<T>): T = retrofit.create(apiClass)
}