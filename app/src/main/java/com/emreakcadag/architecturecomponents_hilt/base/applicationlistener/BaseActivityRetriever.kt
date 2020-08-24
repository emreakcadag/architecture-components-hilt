package com.emreakcadag.architecturecomponents_hilt.base.applicationlistener

import android.view.LayoutInflater
import javax.inject.Inject

/**
 * Created by Emre Akçadağ on 15.08.2020
 */
class BaseActivityRetriever @Inject constructor(baseActivityListener: BaseActivityListener) {

    val layoutInflater = LayoutInflater.from(baseActivityListener.currentActivity)

    val context = baseActivityListener.context

    val activity = baseActivityListener.currentActivity
}