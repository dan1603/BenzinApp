package com.shifthackz.benzinapp.domain

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import com.shifthackz.benzinapp.presentation.widget.SingleLiveEvent
import com.shifthackz.benzinapp.usecases.Interactor
import com.shifthackz.benzinapp.usecases.repository.database.entity.WorkerEntity
import io.reactivex.Single

class SingleWorkerViewModel(application: Application, private val interactor: Interactor) :
    BaseViewModel(application) {

    private val liveDataItem = SingleLiveEvent<WorkerEntity>()

    @SuppressLint("CheckResult")
    fun getItem(id: Int): Single<WorkerEntity> {
        return interactor.getWorkerById(id)
    }

    fun getLiveDataItem(): LiveData<WorkerEntity> {
        return liveDataItem
    }
}