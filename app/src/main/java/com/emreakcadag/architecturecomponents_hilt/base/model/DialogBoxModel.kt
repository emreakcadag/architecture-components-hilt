package com.emreakcadag.architecturecomponents_hilt.base.model

/**
 * Created by Emre Akçadağ on 17.08.2020
 */
data class DialogBoxModel(
    val title: String? = null,
    val description: String? = null,
    val isCancelable: Boolean? = null,
    val buttonList: List<ButtonModel>? = null
)