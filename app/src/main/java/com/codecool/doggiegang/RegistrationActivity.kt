package com.codecool.doggiegang

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.codecool.doggiegang.util.RegistrationContract
import com.codecool.doggiegang.util.Validation
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_registration.topbar

class RegistrationActivity : AppCompatActivity(), RegistrationContract.RegistrationView {

    private var appSettingsPref: SharedPreferences? = null
    private var sharedPrefsEdit: SharedPreferences.Editor? = null
    private var isNightModeOn: Boolean? = null

    //TODO Koin inject this field
    private val validator: RegistrationContract.RegistrationValidation = Validation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        validator.onAttach(this)
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

    fun validateInputs(view: View) {
        validator.validateInputs(usernameField, emailField, passwordField, dogNameField, introductionField, locationField, successText)
    }

    override fun onError(field: TextInputLayout, message: String) {
        field.error = message
    }
}
