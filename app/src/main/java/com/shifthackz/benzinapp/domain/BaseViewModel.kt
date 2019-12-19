package com.shifthackz.benzinapp.domain

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.annotation.StringRes
import com.shifthackz.benzinapp.App

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    fun getContext() = getApplication<App>()
    fun getString(@StringRes id: Int): String = getContext().getString(id)
}