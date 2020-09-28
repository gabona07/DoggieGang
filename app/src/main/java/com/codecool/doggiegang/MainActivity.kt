package com.codecool.doggiegang

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.topbar


class MainActivity : AppCompatActivity() {

    private var appSettingsPref : SharedPreferences ?= null
    private var sharedPrefsEdit : SharedPreferences.Editor ?= null
    private var isNightModeOn : Boolean ?= null

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ProgressBar
        initializeProgress()

        // Initialise SharedPrefs for theme
        appSettingsPref = getSharedPreferences("AppSettingPrefs", Context.MODE_PRIVATE)
        sharedPrefsEdit  = appSettingsPref!!.edit()
        isNightModeOn = appSettingsPref!!.getBoolean("NightMode", false)
        checkPreferencesForTheme()
        handleTopBar()
    }

    private fun initializeProgress() {
      //TODO: Collect data and set progress by async task
    }

    private fun handleTopBar() {
        topbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.set_mode -> {
                    if(isNightModeOn!!){
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        sharedPrefsEdit!!.putBoolean("NightMode", false)
                        sharedPrefsEdit!!.apply()
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        sharedPrefsEdit!!.putBoolean("NightMode", true)
                        sharedPrefsEdit!!.apply()
                    }
                    true
                }
                else -> false
            }
        }
    }

    private fun checkPreferencesForTheme() {
        if(isNightModeOn!!){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    fun goToRegistration(view: View) {
        val intent = Intent(this, RegistrationActivity::class.java)
        startActivity(intent)
    }
}