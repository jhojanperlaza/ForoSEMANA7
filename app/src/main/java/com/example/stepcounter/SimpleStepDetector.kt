package com.example.stepcounter

import kotlin.math.sqrt

class SimpleStepDetector {

    private val threshold = 11f
    private var lastStepTimeNs: Long = 0
    var stepListener: (() -> Unit)? = null

    fun updateAccelerometer(timeNs: Long, x: Float, y: Float, z: Float) {
        val magnitude = sqrt((x * x + y * y + z * z).toDouble()).toFloat()
        if (magnitude > threshold) {
            if (timeNs - lastStepTimeNs > 250_000_000) { // 250 ms
                lastStepTimeNs = timeNs
                stepListener?.invoke()
            }
        }
    }
}
