package com.shifthackz.benzinapp.domain

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import com.shifthackz.benzinapp.presentation.widget.SingleLiveEvent
import com.shifthackz.benzinapp.usecases.Interactor
import com.shifthackz.benzinapp.usecases.repository.database.entity.WorkerEntity

class AllWorkersViewModel(application: Application, private val interactor: Interactor)
    : BaseViewModel(application) {

    private val liveDataItems = SingleLiveEvent<List<WorkerEntity>>()

    @SuppressLint("CheckResult")
    fun getAllItems() {
        interactor.getWorkers()?.subscribe { list -> liveDataItems.postValue(list) }
    }

    fun getLiveDataItems(): LiveData<List<WorkerEntity>> {
        return liveDataItems
    }
}

