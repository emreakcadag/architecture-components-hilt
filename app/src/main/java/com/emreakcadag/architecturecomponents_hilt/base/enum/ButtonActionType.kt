package com.emreakcadag.architecturecomponents_hilt.base.enum

import com.google.gson.annotations.SerializedName

/**
 * Created by Emre Akcadag on 22.08.2020
 *
 */
enum class ButtonActionType {
    @SerializedName("goBack")
    GO_BACK,

    @SerializedName("dismiss")
    DISMISS,

    @SerializedName("request")
    REQUEST,

    @SerializedName("navigate")
    NAVIGATE,

    @SerializedName("dropSession")
    DROP_SESSION,
}