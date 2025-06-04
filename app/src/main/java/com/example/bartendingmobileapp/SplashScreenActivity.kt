package com.example.bartendingmobileapp

import android.annotation.SuppressLint
import android.content.Context.SENSOR_SERVICE
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.bartendingmobileapp.ui.theme.BartendingMobileAppTheme
import kotlin.math.abs
import kotlin.math.sqrt

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : ComponentActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var accelCurrent = 0f
    private var accelLast = 0f

    private val _shakeState = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this, accelSensor, SensorManager.SENSOR_DELAY_UI)

        setContent {
            val context = LocalContext.current
            val showMainScreen by remember { _shakeState }

            if (showMainScreen) {
                LaunchedEffect(Unit) {
                    context.startActivity(Intent(context, MainActivity::class.java))
                    finish()
                }
            } else {
                SplashScreen(
                    onShakeDetected = {
                        _shakeState.value = true
                    }
                )
            }

    }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            accelLast = accelCurrent
            accelCurrent = sqrt((x * x + y * y + z * z).toDouble()).toFloat()
            val delta = abs(accelCurrent - accelLast)

            val shakeThreshold = 12f
            if (delta > shakeThreshold) {
                runOnUiThread {
                    _shakeState.value = true
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)

    }
}
