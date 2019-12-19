package com.shifthackz.benzinapp.usecases.repository.database.pojo

import com.google.gson.annotations.SerializedName
import com.shifthackz.benzinapp.usecases.repository.database.entity.BenzinEntity

class BenzinsResponse {
    @SerializedName("records")
    var records: List<BenzinEntity>? = null
}