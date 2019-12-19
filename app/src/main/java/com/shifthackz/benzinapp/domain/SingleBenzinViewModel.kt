package com.shifthackz.benzinapp.domain

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import com.shifthackz.benzinapp.presentation.widget.SingleLiveEvent
import com.shifthackz.benzinapp.usecases.Interactor
import com.shifthackz.benzinapp.usecases.repository.database.entity.BenzinEntity

class SingleBenzinViewModel(application: Application, private val interactor: Interactor) :
    BaseViewModel(application) {

    private val liveDataItem = SingleLiveEvent<BenzinEntity>()

    @SuppressLint("CheckResult")
    fun getItem(id: Int) {
        interactor.getBenzinById(id).subscribe { item -> liveDataItem.postValue(item) }
    }

    fun getLiveDataItem(): LiveData<BenzinEntity> {
        return liveDataItem
    }
}