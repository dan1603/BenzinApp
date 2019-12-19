package com.shifthackz.benzinapp.usecases.repository.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "history")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var id: Int,
    @SerializedName("time")
    val time: Long,
    @SerializedName("benzin_name")
    val benzinName: String?,
    @SerializedName("liters_count")
    val litersCount: Int,
    @SerializedName("liters_current")
    val litersCurrent: Int,
    @SerializedName("worker_name")
    val workerName: String?,
    @SerializedName("comment")
    val comment: String?
)