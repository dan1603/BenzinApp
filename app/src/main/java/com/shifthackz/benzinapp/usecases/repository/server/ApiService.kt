package com.shifthackz.benzinapp.usecases.repository.server

import com.shifthackz.benzinapp.usecases.repository.database.entity.BenzinEntity
import com.shifthackz.benzinapp.usecases.repository.database.entity.WorkerEntity
import com.shifthackz.benzinapp.usecases.repository.database.pojo.BenzinsResponse
import com.shifthackz.benzinapp.usecases.repository.database.pojo.HistoryResponse
import com.shifthackz.benzinapp.usecases.repository.database.pojo.WorkersResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("/rest/api-v2.php/records/zap_workers")
    fun getWorkers(): Single<Response<WorkersResponse>>

    @GET("/rest/api-v2.php/records/zap_workers/{id}")
    fun getWorkerById(@Path("id") id: Int): Single<WorkerEntity>

    @GET("/rest/api-v2.php/records/zap_benzin")
    fun getBenzins(): Single<Response<BenzinsResponse>>

    @GET("/rest/api-v2.php/records/zap_benzin/{id}")
    fun getBenzinById(@Path("id") id: Int): Single<BenzinEntity>

    @GET("/rest/api-v2.php/records/zap_history")
    fun getHistory(): Single<Response<HistoryResponse>>

    @FormUrlEncoded
    @POST("/rest/api-v1.php/zap_history")
    fun addHistoryItem(
        @Field("time") time: Long,
        @Field("benzin_name") benzinName: String,
        @Field("liters_count") litersCount: Int,
        @Field("liters_current") litersCurrent: Int,
        @Field("worker_name") workerName: String,
        @Field("comment") comment: String
    ): Single<Response<String>>

    @FormUrlEncoded
    @PUT("/rest/api-v1.php/zap_benzin/{id}")
    fun updateBenzinAmount(
        @Path("id") id: Int,
        @Field("zapas") zapas: Int
    ): Single<Response<String>>
}
