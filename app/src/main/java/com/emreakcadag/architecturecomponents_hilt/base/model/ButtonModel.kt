package com.emreakcadag.architecturecomponents_hilt.base.model

import com.emreakcadag.architecturecomponents_hilt.base.enum.ButtonActionType

/**
 * Created by Emre Akçadağ on 17.08.2020
 */
data class ButtonModel(
    val text: String? = null,
    val themeType: String? = null,
    val actionType: ButtonActionType? = null
)