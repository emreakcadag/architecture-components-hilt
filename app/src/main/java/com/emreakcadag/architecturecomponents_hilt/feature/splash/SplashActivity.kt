package com.emreakcadag.architecturecomponents_hilt.feature.splash

import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.emreakcadag.architecturecomponents_hilt.R
import com.emreakcadag.architecturecomponents_hilt.base.navigator.Navigator

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashActivity : AppCompatActivity(R.layout.activity_splash) {

    override fun onResume() {
        super.onResume()

        Handler(Looper.getMainLooper()).postDelayed({
            Navigator().openMainActivity(this@SplashActivity)
        }, 1000)
    }
}