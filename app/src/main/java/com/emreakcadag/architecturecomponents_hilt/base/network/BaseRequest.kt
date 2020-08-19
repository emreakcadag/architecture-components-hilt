package com.emreakcadag.architecturecomponents_hilt.base.network

import com.google.gson.annotations.SerializedName

/**
 * Created by Emre Akçadağ on 17.08.2020
 */
open class BaseRequest {
    @SerializedName("language")
    var language: String? = "TR" // todo emreakcadag = null
}