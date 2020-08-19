package com.emreakcadag.architecturecomponents_hilt.base.navigator

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.emreakcadag.architecturecomponents_hilt.feature.main.ui.MainActivity

/**
 * Created by Emre Akçadağ on 15.08.2020
 */
class Navigator {

    private fun Context?.openActivity(intent: Intent?, requestCode: RequestCode? = null) =
        this?.takeIf { intent != null }?.run {
            requestCode?.let { (this as? Activity)?.startActivityForResult(intent, it.type) }
                ?: startActivity(intent)
        }

    fun openMainActivity(context: Context?) {
        context?.openActivity(MainActivity.newIntent(context))
    }
}