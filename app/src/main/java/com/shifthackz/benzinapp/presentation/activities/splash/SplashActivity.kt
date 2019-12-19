package com.shifthackz.benzinapp.presentation.activities.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.shifthackz.benzinapp.R
import com.shifthackz.benzinapp.presentation.activities.main.MainActivity
import com.shifthackz.benzinapp.utils.auth.Authentication
import com.shifthackz.benzinapp.utils.auth.keyguard.AuthCallback
import com.shifthackz.benzinapp.utils.showToast

class SplashActivity : AppCompatActivity() {

    private val timer = 3000L

    private val callback = object : AuthCallback {
        override fun onAuthSuccess() {
            startActivity(MainActivity.newInstance(this@SplashActivity))
            finish()
        }

        override fun onAuthError(errorMessage: String) {
            showToast(errorMessage)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        requestAuthentication()
//        Handler().postDelayed({
//            startActivity(MainActivity.newInstance(this))
//            finish()
//        }, timer)
    }

    private fun requestAuthentication() {
        Handler().postDelayed({
            Authentication.authenticate(this, callback)
        }, timer)
    }
}
