package com.codecool.doggiegang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeProgress()
    }

    private fun initializeProgress() {
      //TODO: Collect data and set progress by async task
    }
}