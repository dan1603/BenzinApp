package com.shifthackz.benzinapp.usecases.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shifthackz.benzinapp.usecases.repository.database.dao.BenzinDao
import com.shifthackz.benzinapp.usecases.repository.database.dao.HistoryDao
import com.shifthackz.benzinapp.usecases.repository.database.dao.WorkerDao
import com.shifthackz.benzinapp.usecases.repository.database.entity.BenzinEntity
import com.shifthackz.benzinapp.usecases.repository.database.entity.HistoryEntity
import com.shifthackz.benzinapp.usecases.repository.database.entity.WorkerEntity

@Database(entities = [
    WorkerEntity::class,
    BenzinEntity::class,
    HistoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workerDao(): WorkerDao
    abstract fun benzinDao(): BenzinDao
    abstract fun historyDao(): HistoryDao
}