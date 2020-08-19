package com.emreakcadag.architecturecomponents_hilt

import android.app.Application
import com.emreakcadag.architecturecomponents_hilt.base.applicationlistener.BaseActivityListener
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * Created by Emre Akçadağ on 15.08.2020
 */
@HiltAndroidApp
class MyApplication : Application() {

    @Inject
    lateinit var baseActivityListener: BaseActivityListener

    override fun onCreate() {
        super.onCreate()

        registerActivityLifecycleCallbacks(baseActivityListener)
    }
}