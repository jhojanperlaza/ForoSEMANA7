package com.example.stepcounter.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StepDao {

    @Query("SELECT steps FROM steps WHERE date = :date LIMIT 1")
    suspend fun getSteps(date: String): Int?          // null si no existe

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: StepEntity)            // guarda / actualiza

    @Query("SELECT * FROM steps ORDER BY date")
    suspend fun getAll(): List<StepEntity>            // para HistoryActivity
}

