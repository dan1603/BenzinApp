package com.shifthackz.benzinapp.domain

import android.app.Application
import androidx.lifecycle.LiveData
import com.shifthackz.benzinapp.presentation.widget.SingleLiveEvent
import com.shifthackz.benzinapp.usecases.Interactor
import com.shifthackz.benzinapp.usecases.repository.database.entity.HistoryEntity

class SingleHistoryViewModel(application: Application, private val interactor: Interactor) :
    BaseViewModel(application) {

    private val liveDataItem = SingleLiveEvent<HistoryEntity>()

    fun addItem(historyEntity: HistoryEntity) {
        interactor.addHistoryItem(historyEntity).subscribe()
    }

    fun getLiveDataItem(): LiveData<HistoryEntity> {
        return liveDataItem
    }
}