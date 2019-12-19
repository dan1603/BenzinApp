package com.shifthackz.benzinapp.utils.auth.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shifthackz.benzinapp.R
import com.shifthackz.benzinapp.utils.auth.Authentication
import com.shifthackz.benzinapp.utils.auth.keyguard.KeyguardUtil

const val CODE_AUTHENTICATION_VERIFICATION = 241

class AuthActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun newInstance(context: Context): Intent {
            val intent = Intent(context, AuthActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
        startAuthentication()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CODE_AUTHENTICATION_VERIFICATION -> {
                when (resultCode) {
                    Activity.RESULT_OK  -> onAuthSuccess()
                    else                -> onAuthError(getString(R.string.error_fail))
                }
            }
        }
    }

    private fun setupUI() {
        setContentView(R.layout.activity_auth)
    }

    private fun startAuthentication() {
        if (KeyguardUtil(this).checkIfKeyguardSecure()) {
            startActivityForResult((KeyguardUtil(this).getKeyguardIntent()),
                CODE_AUTHENTICATION_VERIFICATION)
        }
    }

    private fun onAuthSuccess() {
        Authentication.authCallback.onAuthSuccess()
        finish()
    }

    private fun onAuthError(message: String) {
        Authentication.authCallback.onAuthError(message)
        finish()
    }
}
