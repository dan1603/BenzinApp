package com.shifthackz.benzinapp.usecases

import com.shifthackz.benzinapp.usecases.repository.AppRepository
import com.shifthackz.benzinapp.usecases.repository.database.entity.BenzinEntity
import com.shifthackz.benzinapp.usecases.repository.database.entity.HistoryEntity
import com.shifthackz.benzinapp.usecases.repository.database.entity.WorkerEntity
import io.reactivex.Single

class Interactor(private val repository: AppRepository) {

    fun getWorkers(): Single<List<WorkerEntity>>? {
        return repository.getWorkers()
    }

    fun getWorkerById(id: Int): Single<WorkerEntity> {
        return repository.getWorkerById(id)
    }

    fun getBenzins(): Single<List<BenzinEntity>>? {
        return repository.getBenzins()
    }

    fun getBenzinById(id: Int): Single<BenzinEntity> {
        return repository.getBenzinById(id)
    }

    fun getHistory(): Single<List<HistoryEntity>>? {
        return repository.getHistory()
    }

    fun addHistoryItem(historyEntity: HistoryEntity): Single<HistoryEntity> {
        return repository.addHistoryItem(historyEntity)
    }
}