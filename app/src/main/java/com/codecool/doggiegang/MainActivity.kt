package com.codecool.doggiegang

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialise SharedPrefs
        val appSettingsPref = getSharedPreferences("AppSettingPrefs", Context.MODE_PRIVATE)
        val sharedPrefsEdit : SharedPreferences.Editor = appSettingsPref.edit()
        val isNightModeOn: Boolean = appSettingsPref.getBoolean("NightMode", false)
        initializeProgress()
        setNightModeListener(isNightModeOn, sharedPrefsEdit)
        saveThemeState(appSettingsPref)

        if(isNightModeOn){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            set_theme.text = "Disable Dark Mode"
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            set_theme.text = "Enable Dark Mode"
        }
    }

    private fun initializeProgress() {
      //TODO: Collect data and set progress by async task
    }

    private fun setNightModeListener(isNightModeOn: Boolean, sharedPrefsEdit: SharedPreferences.Editor) {

        set_theme.setOnClickListener(View.OnClickListener {
            if(isNightModeOn){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPrefsEdit.putBoolean("NightMode", false)
                sharedPrefsEdit.apply()

                set_theme.text = "Enable Dark Mode"
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPrefsEdit.putBoolean("NightMode", true)
                sharedPrefsEdit.apply()
                set_theme.text = "Disable Dark Mode"
            }
        })
    }

    private fun saveThemeState(appSettingsPref: SharedPreferences) {
        val isNightModeOn : Boolean? = appSettingsPref.getBoolean("NightMode", false)
        if (isNightModeOn!!) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            set_theme.text = "Enable light mode"
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            set_theme.text = "Enable dark mode"
        }
    }
}