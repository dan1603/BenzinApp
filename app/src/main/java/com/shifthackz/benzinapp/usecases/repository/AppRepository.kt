package com.shifthackz.benzinapp.usecases.repository

import com.shifthackz.benzinapp.usecases.repository.database.AppDatabase
import com.shifthackz.benzinapp.usecases.repository.database.entity.BenzinEntity
import com.shifthackz.benzinapp.usecases.repository.database.entity.HistoryEntity
import com.shifthackz.benzinapp.usecases.repository.database.entity.WorkerEntity
import com.shifthackz.benzinapp.usecases.repository.server.ServerCommunicator
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppRepository(
    private val serverCommunicator: ServerCommunicator,
    private val mainDatabase: AppDatabase
) {

    fun getWorkers(): Single<List<WorkerEntity>>? {
        return serverCommunicator.getWorkers()
            .flatMap { list ->
                list.body()?.records?.let {
                    mainDatabase.workerDao().insertList(it)
                }
                Single.just(mainDatabase.workerDao().getAll())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getWorkerById(id: Int): Single<WorkerEntity> {
        return serverCommunicator.getWorkerById(id)
            .map {
                mainDatabase.workerDao().getById(id)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

    fun getBenzins(): Single<List<BenzinEntity>>? {
        return serverCommunicator.getBenzins()
            .flatMap { list ->
                list.body()?.records?.let {
                    mainDatabase.benzinDao().insertList(it)
                }
                Single.just(mainDatabase.benzinDao().getAll())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getBenzinById(id: Int): Single<BenzinEntity> {
        return serverCommunicator.getBenzinById(id)
            .map {
                mainDatabase.benzinDao().getById(id)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getHistory(): Single<List<HistoryEntity>>? {
        return serverCommunicator.getHistory()
            .flatMap { list ->
                list.body()?.records?.let {
                    mainDatabase.historyDao().insertList(it)
                }
                Single.just(mainDatabase.historyDao().getAll())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun addHistoryItem(historyEntity: HistoryEntity): Single<HistoryEntity> {
        return serverCommunicator.addHistoryItem(historyEntity)
            .map {
                if (it.isSuccessful) {
                    it.body()?.toInt()?.let {
                        historyEntity.id = it
                    }
                    mainDatabase.historyDao().insert(historyEntity)

                    updateBenzinZapas(mainDatabase.benzinDao()
                        .getByName(historyEntity.benzinName.toString()).first().id,
                    mainDatabase.benzinDao()
                        .getByName(historyEntity.benzinName.toString()).first().zapas
                            + historyEntity.litersCount).subscribe()
                }
                mainDatabase.historyDao().getById(it.body()!!.toInt())
            }
            .doOnError { it.printStackTrace() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun updateBenzinZapas(id: Int, newAmount: Int): Single<String?> {
        return serverCommunicator.updateBenzinZapas(id, newAmount)
            .map { it.body()?.let { return@map it } }
            .doOnError { it.printStackTrace() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}