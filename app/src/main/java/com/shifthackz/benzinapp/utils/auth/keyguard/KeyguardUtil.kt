package com.shifthackz.benzinapp.utils.auth.keyguard

import android.annotation.SuppressLint
import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import com.shifthackz.benzinapp.R

class KeyguardUtil(private val context: Context) {

    @SuppressLint("NewApi")
    fun getKeyguardIntent(): Intent =
        getKeyguardManager().createConfirmDeviceCredentialIntent(
            context.getString(R.string.keyguard_title),
            context.getString(R.string.keyguard_description))

    fun checkIfKeyguardSecure(): Boolean = getKeyguardManager().isKeyguardSecure

    private fun getKeyguardManager() =
        context.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
}