package com.shifthackz.benzinapp.usecases.repository.database.dao

import androidx.room.*
import com.shifthackz.benzinapp.usecases.repository.database.entity.HistoryEntity

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history")
    fun getAll(): List<HistoryEntity>

    @Query("SELECT * FROM history WHERE id = :id")
    fun getById(id: Int): HistoryEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(list: List<HistoryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: HistoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(item: HistoryEntity)

    @Update
    fun updateAll(list: List<HistoryEntity>)

    @Delete
    fun delete(item: HistoryEntity)
}