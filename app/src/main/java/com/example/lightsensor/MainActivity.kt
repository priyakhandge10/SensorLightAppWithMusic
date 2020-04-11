package com.example.lightsensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity(),SensorEventListener {

    var Sensor: Sensor? = null
    var SensorManager: SensorManager? = null
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

       }
    var mp:MediaPlayer?=null
    var isRunning=false

    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.values[0]>40 && !isRunning){
            isRunning=true
            try {

                var mp=MediaPlayer()
                mp.setDataSource("https://file-examples.com/wp-content/uploads/2017/11/file_example_MP3_5MG.mp3")
                mp.prepare()
            }catch (ex:Exception){
                ex.printStackTrace()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        Sensor = SensorManager!!.getDefaultSensor(android.hardware.Sensor.TYPE_LIGHT)

    }

    override fun onResume() {
        super.onResume()
        SensorManager!!.registerListener(this,Sensor,android.hardware.SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        SensorManager!!.unregisterListener(this)
    }
}
