package com.example.kompas

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.getSystemService

class MainActivity : AppCompatActivity(),SensorEventListener {
    var manager:SensorManager?  = null
    var currentsDigress: Float = 0.0f
    var tvDegri:TextView? = null
    lateinit var tvDinamic:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
         tvDegri = findViewById<TextView>(R.id.tv_degris)
        tvDinamic = findViewById(R.id.imgDinamic)
    }

    override fun onResume() {
        super.onResume()
        manager?.registerListener(this,manager?.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        super.onPause()
        manager?.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        var degri = event?.values?.get(0)?.toInt()!!
        tvDegri?.text = degri.toString()
        val rot = RotateAnimation(currentsDigress,  (-degri).toFloat(),Animation.RELATIVE_TO_SELF,0.5f
            ,Animation.RELATIVE_TO_SELF,0.5f)
        rot.duration = 210
        rot.fillAfter =true
        currentsDigress = (-degri).toFloat()
        tvDinamic.startAnimation(rot)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }


}