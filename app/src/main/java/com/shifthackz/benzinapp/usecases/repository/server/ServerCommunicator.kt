package com.shifthackz.benzinapp.usecases.repository.server

import android.util.Log
import com.shifthackz.benzinapp.usecases.repository.database.entity.BenzinEntity
import com.shifthackz.benzinapp.usecases.repository.database.entity.HistoryEntity
import com.shifthackz.benzinapp.usecases.repository.database.entity.WorkerEntity
import com.shifthackz.benzinapp.usecases.repository.database.pojo.BenzinsResponse
import com.shifthackz.benzinapp.usecases.repository.database.pojo.HistoryResponse
import com.shifthackz.benzinapp.usecases.repository.database.pojo.WorkersResponse
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.util.concurrent.TimeUnit

class ServerCommunicator(private val apiService: ApiService) {

    companion object {
        private const val DEFAULT_TIMEOUT = 10
        private const val DEFAULT_RETRY_ATTEMPTS = 4L
    }

    fun getWorkers(): Single<Response<WorkersResponse>> {
        return apiService.getWorkers()
            .compose(singleTransformer())
            .doOnError { t: Throwable -> Log.d("ServerCommunicator", t.message) }
    }

    fun getWorkerById(id: Int): Single<WorkerEntity> {
        return apiService.getWorkerById(id).compose(singleTransformer())
    }

    fun getBenzins(): Single<Response<BenzinsResponse>> {
        return apiService.getBenzins()
            .compose(singleTransformer())
            .doOnError { it.printStackTrace() }
    }

    fun getBenzinById(id: Int): Single<BenzinEntity> {
        return apiService.getBenzinById(id)
            .compose(singleTransformer())
            .doOnError { it.printStackTrace() }
    }

    fun getHistory(): Single<Response<HistoryResponse>> {
        return apiService.getHistory()
            .compose(singleTransformer())
            .doOnError { it.printStackTrace() }
    }

    fun addHistoryItem(historyEntity: HistoryEntity): Single<Response<String>> {
        return apiService.addHistoryItem(historyEntity.time,
            historyEntity.benzinName.toString(),
            historyEntity.litersCount,
            historyEntity.litersCurrent,
            historyEntity.workerName.toString(),
            historyEntity.comment.toString())
            .compose(singleTransformer())
            .doOnError { it.printStackTrace() }
    }

    fun updateBenzinZapas(id: Int, newZapas: Int): Single<Response<String>> {
        return apiService.updateBenzinAmount(id, newZapas)
            .compose(singleTransformer())
            .doOnError { it.printStackTrace() }
    }

    private fun <T> singleTransformer(): SingleTransformer<T, T> = SingleTransformer {
        it.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .timeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .retry(DEFAULT_RETRY_ATTEMPTS)
    }

    private fun <T> observableTransformer(): ObservableTransformer<T, T> = ObservableTransformer {
        it.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .timeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .retry(DEFAULT_RETRY_ATTEMPTS)
    }
}
