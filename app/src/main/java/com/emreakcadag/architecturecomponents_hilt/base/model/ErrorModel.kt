package com.emreakcadag.architecturecomponents_hilt.base.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Emre Akçadağ on 17.08.2020
 */
data class ErrorModel(
    @SerializedName("code")
    val code: Int? = null,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("dialogBox")
    val dialogBox: DialogBoxModel? = null
)