package com.emreakcadag.architecturecomponents_hilt.base.applicationlistener

import android.view.LayoutInflater

/**
 * Created by Emre Akçadağ on 15.08.2020
 */
class BaseActivityRetriever(baseActivityListener: BaseActivityListener) {

    val layoutInflater = LayoutInflater.from(baseActivityListener.currentActivity)

    val context = baseActivityListener.context

    val activity = baseActivityListener.currentActivity
}