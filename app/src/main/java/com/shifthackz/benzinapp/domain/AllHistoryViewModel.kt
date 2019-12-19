package com.shifthackz.benzinapp.domain

import android.annotation.SuppressLint
import android.app.Application
import com.shifthackz.benzinapp.presentation.widget.SingleLiveEvent
import com.shifthackz.benzinapp.usecases.Interactor
import com.shifthackz.benzinapp.usecases.repository.database.entity.HistoryEntity

class AllHistoryViewModel(application: Application, private val interactor: Interactor)
    : BaseViewModel(application) {

    private val liveDataItems = SingleLiveEvent<List<HistoryEntity>>()

    @SuppressLint("CheckResult")
    fun getAllItems() {
        interactor.getHistory()?.subscribe { list -> liveDataItems.postValue(list) }
    }

    fun getLiveDataItems() = liveDataItems
}