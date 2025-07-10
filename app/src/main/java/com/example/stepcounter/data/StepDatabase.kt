package com.example.stepcounter.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [StepEntity::class], version = 1, exportSchema = false)
abstract class StepDatabase : RoomDatabase() {
    abstract fun stepDao(): StepDao

    companion object {
        @Volatile private var INSTANCE: StepDatabase? = null

        fun getInstance(context: Context): StepDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    StepDatabase::class.java,
                    "steps_db"
                ).build().also { INSTANCE = it }
            }
    }
}