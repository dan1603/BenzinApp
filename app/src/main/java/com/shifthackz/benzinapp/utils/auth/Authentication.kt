package com.shifthackz.benzinapp.utils.auth

import android.app.Activity
import com.shifthackz.benzinapp.utils.auth.activity.AuthActivity
import com.shifthackz.benzinapp.utils.auth.keyguard.AuthCallback

object Authentication {
    lateinit var authCallback: AuthCallback

    fun authenticate(activity: Activity, authCallback: AuthCallback) {
        this.authCallback = authCallback
        activity.applicationContext.startActivity(AuthActivity.newInstance(activity))
    }
}