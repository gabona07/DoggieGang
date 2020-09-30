package com.codecool.doggiegang.util

import android.util.Patterns
import android.view.View
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

class Validation : RegistrationContract.RegistrationValidation{

    private var view: RegistrationContract.RegistrationView? = null

    companion object {
        private val PASSWORD_PATTERN =  Pattern.compile("^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=!])" +   //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$")

        private val NAME_PATTERN = Pattern.compile("^([a-zA-Z]{2,}\\s[a-zA-Z]{1,}'?-?[a-zA-Z]{2,}\\s?([a-zA-Z]{1,})?)")
    }

    override fun validateInputs(name: TextInputLayout, email: TextInputLayout, password: TextInputLayout, success: TextView) {
        validateName(name)
        validateEmail(email)
        validatePassword(password)
        if (validateName(name) && validateEmail(email) && validatePassword(password)) {
            success.visibility = View.VISIBLE
        }
    }

    private fun validateName(nameField: TextInputLayout): Boolean {
        val name = nameField.editText?.text.toString().trim()
        if (name.isEmpty()) {
            view?.onError(nameField,"Field can't be empty!")
            return false
        } else if (!NAME_PATTERN.matcher(name).matches()) {
            view?.onError(nameField,"Invalid name format!")
            return false
        }
        return true
    }

    private fun validateEmail(emailField: TextInputLayout): Boolean {
        val email = emailField.editText?.text.toString().trim()
        if (email.isEmpty()) {
            view?.onError(emailField,"Field can't be empty!")
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view?.onError(emailField,"Wrong e-mail format!")
            return false
        }
        return true
    }

    private fun validatePassword(passwordField: TextInputLayout): Boolean {
        val password = passwordField.editText?.text.toString().trim()
        if (password.isEmpty()) {
            view?.onError(passwordField,"Field can't be empty!")
            return false
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            view?.onError(passwordField, "Password too weak, it must contain one lowercase, one uppercase, one digit and one special character.")
            return false
        }
        return true
    }

    override fun onAttach(view: RegistrationContract.RegistrationView) {
        this.view = view
    }

    override fun onDetach() {
        this.view = null
    }
}