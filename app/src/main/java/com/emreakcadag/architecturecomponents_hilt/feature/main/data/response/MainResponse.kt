package com.emreakcadag.architecturecomponents_hilt.feature.main.data.response

import com.emreakcadag.architecturecomponents_hilt.base.network.BaseResponse

/**
 * Created by Emre Akçadağ on 17.08.2020
 */
data class MainResponse(
    val copyright: String? = null,
    val date: String? = null,
    val explanation: String? = null,
    val hdurl: String? = null,
    val mediaType: String? = null,
    val serviceVersion: String? = null,
    val title: String? = null,
    val url: String? = null
) : BaseResponse()