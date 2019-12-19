package com.shifthackz.benzinapp.usecases.repository.database.pojo

import com.google.gson.annotations.SerializedName
import com.shifthackz.benzinapp.usecases.repository.database.entity.HistoryEntity

class HistoryResponse {
    @SerializedName("records")
    var records: List<HistoryEntity>? = null
}