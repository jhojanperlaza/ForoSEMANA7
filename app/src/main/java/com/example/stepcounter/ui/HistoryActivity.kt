package com.example.stepcounter.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.stepcounter.R
import com.example.stepcounter.databinding.ActivityHistoryBinding
import com.example.stepcounter.data.StepDatabase
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao = StepDatabase.getInstance(this).stepDao()

        lifecycleScope.launch {
            val entries = dao.getAll().mapIndexed { index, stepEntry ->
                Entry(index.toFloat(), stepEntry.steps.toFloat())
            }

            val dataSet = LineDataSet(entries, getString(R.string.history)).apply {
                setDrawValues(false)
                setDrawCircles(false)
            }

            binding.lineChart.data = LineData(dataSet)
            binding.lineChart.invalidate()
        }
    }
}
