package com.shifthackz.benzinapp.utils.auth.keyguard

interface AuthCallback {
    fun onAuthSuccess()
    fun onAuthError(errorMessage: String)
}