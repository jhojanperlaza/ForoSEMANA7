package com.example.stepcounter.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "steps")
data class StepEntity(
    @PrimaryKey val date: String,    // yyyy-MM-dd
    val steps: Int
)
