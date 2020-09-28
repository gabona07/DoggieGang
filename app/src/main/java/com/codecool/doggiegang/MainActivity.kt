package com.codecool.doggiegang

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*


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

        // Toolbar
        setSupportActionBar(toolbar)

        // Initialise SharedPrefs for theme
        appSettingsPref = getSharedPreferences("AppSettingPrefs", Context.MODE_PRIVATE)
        sharedPrefsEdit  = appSettingsPref!!.edit()
        isNightModeOn = appSettingsPref!!.getBoolean("NightMode", false)
        checkPreferencesForTheme()
        saveThemeStateToPrefs()
    }

    private fun initializeProgress() {
      //TODO: Collect data and set progress by async task
    }

    private fun checkPreferencesForTheme() {
        if(isNightModeOn!!){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun saveThemeStateToPrefs() {
        val isNightModeOn : Boolean? = appSettingsPref!!.getBoolean("NightMode", false)
        if (isNightModeOn!!) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.set_mode) {
            println("ITT VAGYOK!")
            if(isNightModeOn!!){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPrefsEdit!!.putBoolean("NightMode", false)
                sharedPrefsEdit!!.apply()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPrefsEdit!!.putBoolean("NightMode", true)
                sharedPrefsEdit!!.apply()
            }
            saveThemeStateToPrefs()
        }
        return super.onOptionsItemSelected(item)
    }
}