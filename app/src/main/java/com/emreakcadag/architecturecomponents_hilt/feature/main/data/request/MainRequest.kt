package com.emreakcadag.architecturecomponents_hilt.feature.main.data.request

import com.emreakcadag.architecturecomponents_hilt.base.network.BaseRequest

/**
 * Created by Emre Akçadağ on 17.08.2020
 */
class MainRequest(
    val showDialog: Boolean? = false
) : BaseRequest()