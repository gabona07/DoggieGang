package com.codecool.doggiegang

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_registration.topbar

class RegistrationActivity : AppCompatActivity() {

    private var appSettingsPref: SharedPreferences? = null
    private var sharedPrefsEdit: SharedPreferences.Editor? = null
    private var isNightModeOn: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        appSettingsPref = getSharedPreferences("AppSettingPrefs", Context.MODE_PRIVATE)
        sharedPrefsEdit = appSettingsPref!!.edit()
        isNightModeOn = appSettingsPref!!.getBoolean("NightMode", false)
        checkPreferencesForTheme()
        handleTopBar()
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

}
