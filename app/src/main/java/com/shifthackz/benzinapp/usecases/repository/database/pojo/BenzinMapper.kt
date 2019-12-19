package com.shifthackz.benzinapp.usecases.repository.database.pojo

object BenzinMapper {
    fun getBenzinIdByName(name: String): Int = when (name) {
        "АИ-92" -> 1
        "АИ-95 Euro" -> 2
        "АИ-95 Pulls" -> 3
        "ДТ Euro" -> 4
        "ДТ Pulls" -> 5
        else -> 0
    }
}