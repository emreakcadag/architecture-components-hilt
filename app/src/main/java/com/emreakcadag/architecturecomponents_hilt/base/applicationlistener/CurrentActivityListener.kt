package com.emreakcadag.architecturecomponents_hilt.base.applicationlistener

import android.app.Activity

/**
 * Created by Emre Akçadağ on 15.08.2020
 */
interface CurrentActivityListener {
    val currentActivity: Activity?
}