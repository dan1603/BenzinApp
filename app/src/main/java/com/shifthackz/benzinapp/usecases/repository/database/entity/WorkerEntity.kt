package com.shifthackz.benzinapp.usecases.repository.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "workers")
data class WorkerEntity(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("father_name")
    val fatherName: String,
    @SerializedName("birthday")
    val birthday: Long,
    @SerializedName("position")
    val position: String,
    @SerializedName("phone")
    val phone: String
)


