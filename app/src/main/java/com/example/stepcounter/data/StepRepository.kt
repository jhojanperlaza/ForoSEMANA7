package com.example.stepcounter

import com.example.stepcounter.data.StepDao
import com.example.stepcounter.data.StepEntity

class StepRepository(private val dao: StepDao) {

    suspend fun getStepsForDate(date: String): Int =
        dao.getSteps(date) ?: 0

    suspend fun saveSteps(date: String, steps: Int) =
        dao.insert(StepEntity(date, steps))

    suspend fun getAllSteps() = dao.getAll()
}
