package com.emreakcadag.architecturecomponents_hilt.base.applicationlistener

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.emreakcadag.architecturecomponents_hilt.base.extension.logDebug
import java.util.*
import javax.inject.Inject

/**
 * Created by Emre Akçadağ on 15.08.2020
 */
class BaseActivityListener @Inject constructor() : Application.ActivityLifecycleCallbacks {

    var currentActivity: Activity? = null
    var context: Context? = null
    private var currentActivityStack: MutableList<Activity> = ArrayList()

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        currentActivity = activity
        context = activity
        currentActivityStack.add(activity)

        logDebug("${activity::class.simpleName} onActivityCreated")
    }

    fun isActivityRunning(activityClass: Class<out Activity>): Boolean {
        for (activity in currentActivityStack) {
            if (activity.javaClass == activityClass) {
                return true
            }
        }
        return false
    }

    override fun onActivityStarted(activity: Activity) {
        currentActivity = activity
    }

    override fun onActivityResumed(activity: Activity) {
        currentActivity = activity
    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivityDestroyed(activity: Activity) {
        if (activity == currentActivity) {
            currentActivity = null
        }
        logDebug("${activity::class.simpleName} onActivityDestroyed")
        currentActivityStack.remove(activity)
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {

    }
}