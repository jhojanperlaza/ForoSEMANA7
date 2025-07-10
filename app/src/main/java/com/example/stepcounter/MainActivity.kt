package com.example.stepcounter

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.stepcounter.data.StepDatabase          // ← IMPORT que faltaba
import com.example.stepcounter.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat                          // ← fechas
import java.util.*

/**
 * Pantalla principal: muestra los pasos del día y guarda cada nuevo paso en Room.
 */
class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var binding: ActivityMainBinding

    // ─── sensores ───────────────────────────────────────────────────────────────
    private lateinit var sensorManager: SensorManager
    private val stepDetector = SimpleStepDetector().apply {
        stepListener = { onStepDetected() }                // callback al detectar paso
    }

    // ─── ViewModel (con Factory) ───────────────────────────────────────────────
    private val viewModel: StepViewModel by viewModels {
        StepViewModelFactory( StepDatabase.getInstance(this).stepDao() )
    }

    // ─── utilidades ────────────────────────────────────────────────────────────
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private var stepsToday = 0

    // ─── ciclo de vida ─────────────────────────────────────────────────────────
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //-- sensor manager
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        //-- carga pasos almacenados de hoy
        val today = dateFormat.format(Date())
        lifecycleScope.launch {
            stepsToday = viewModel.getStepsForDate(today)
            binding.tvSteps.text = stepsToday.toString()
        }

    }

    // ─── callbacks del acelerómetro ────────────────────────────────────────────
    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(
            this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            stepDetector.updateAccelerometer(
                event.timestamp,
                event.values[0], event.values[1], event.values[2]
            )
        }
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit

    // ─── cuando se detecta un paso ─────────────────────────────────────────────
    private fun onStepDetected() {
        stepsToday++
        binding.tvSteps.text = stepsToday.toString()

        val today = dateFormat.format(Date())
        lifecycleScope.launch {
            viewModel.saveSteps(today, stepsToday)
        }
    }
}