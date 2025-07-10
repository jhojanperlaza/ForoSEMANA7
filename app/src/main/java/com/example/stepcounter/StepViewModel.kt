package com.example.stepcounter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StepViewModel(private val repo: StepRepository) : ViewModel() {

    suspend fun getStepsForDate(date: String): Int =
        withContext(Dispatchers.IO) { repo.getStepsForDate(date) }

    suspend fun saveSteps(date: String, steps: Int) =
        withContext(Dispatchers.IO) { repo.saveSteps(date, steps) }

    suspend fun getAll() = withContext(Dispatchers.IO) { repo.getAllSteps() }
}
