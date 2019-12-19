package com.shifthackz.benzinapp.usecases.repository.database.dao

import androidx.room.*
import com.shifthackz.benzinapp.usecases.repository.database.entity.BenzinEntity

@Dao
interface BenzinDao {
    @Query("SELECT * FROM benzins")
    fun getAll(): List<BenzinEntity>

    @Query("SELECT * FROM benzins WHERE name = :name")
    fun getByName(name: String): List<BenzinEntity>

    @Query("SELECT * FROM benzins WHERE id = :id")
    fun getById(id: Int): BenzinEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(list: List<BenzinEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(item: BenzinEntity)

    @Update
    fun updateAll(list: List<BenzinEntity>)

    @Delete
    fun delete(item: BenzinEntity)
}