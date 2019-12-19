package com.shifthackz.benzinapp.usecases.repository.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "benzins")
data class BenzinEntity(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price_sell")
    val priceSell: Float,
    @SerializedName("price_buy")
    val priceBuy: Float,
    @SerializedName("zapas")
    val zapas: Int
)