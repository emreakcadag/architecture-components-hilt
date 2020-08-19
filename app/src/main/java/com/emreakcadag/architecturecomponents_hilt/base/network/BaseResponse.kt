package com.emreakcadag.architecturecomponents_hilt.base.network

import com.emreakcadag.architecturecomponents_hilt.base.model.DialogBoxModel
import com.google.gson.annotations.SerializedName

/**
 * Created by Emre Akçadağ on 17.08.2020
 */
open class BaseResponse {

    @SerializedName("dialogBox")
    var dialogBox: DialogBoxModel? = null

    @SerializedName("message")
    var message: String? = null
}