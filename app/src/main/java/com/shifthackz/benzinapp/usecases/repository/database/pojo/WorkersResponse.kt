package com.shifthackz.benzinapp.usecases.repository.database.pojo

import com.google.gson.annotations.SerializedName
import com.shifthackz.benzinapp.usecases.repository.database.entity.WorkerEntity

class WorkersResponse {
    @SerializedName("records")
    var records: List<WorkerEntity>? = null
}