package com.shifthackz.benzinapp.usecases.repository.database.dao

import androidx.room.*
import com.shifthackz.benzinapp.usecases.repository.database.entity.WorkerEntity

@Dao
interface WorkerDao {
    @Query("SELECT * FROM workers")
    fun getAll(): List<WorkerEntity>

    @Query("SELECT * FROM workers WHERE id = :id")
    fun getById(id: Int): WorkerEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(list: List<WorkerEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(workerEntity: WorkerEntity)

    @Update
    fun updateAll(list: List<WorkerEntity>)

    @Delete
    fun delete(workerEntity: WorkerEntity)
}