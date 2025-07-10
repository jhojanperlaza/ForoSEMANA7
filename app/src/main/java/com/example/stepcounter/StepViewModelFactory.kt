package com.example.stepcounter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stepcounter.data.StepDao

/**
 * Provee un [StepViewModel] con su repositorio ya inyectado.
 */
class StepViewModelFactory(
    private val dao: StepDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StepViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StepViewModel(StepRepository(dao)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}