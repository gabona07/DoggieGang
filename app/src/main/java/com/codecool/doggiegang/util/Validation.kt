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
    }

    override fun validateInputs(usernameField : TextInputLayout, emailField  : TextInputLayout, passwordField : TextInputLayout, dogNameField  : TextInputLayout, introductionField  : TextInputLayout, locationField  : TextInputLayout, successText : TextView) {
        validateIfEmpty(usernameField)
        validateEmail(emailField)
        validatePassword(passwordField)
        validateIfEmpty(dogNameField)
        validateIfEmpty(introductionField)
        validateIfEmpty(locationField)
        if (validateIfEmpty(usernameField) && validateEmail(emailField) && validatePassword(passwordField) &&
            validateIfEmpty(dogNameField) && validateIfEmpty(introductionField) && validateIfEmpty(locationField)) {
            successText.visibility = View.VISIBLE
        }
    }

    private fun validateEmail(emailField: TextInputLayout): Boolean {
        val email = emailField.editText?.text.toString().trim()
        if (email.isEmpty()) {
            view?.onError(emailField,"Field can't be empty!")
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view?.onError(emailField,"Invalid email format!")
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
            view?.onError(passwordField, "Password is too weak!")
            return false
        }
        return true
    }

    private fun validateIfEmpty(field : TextInputLayout): Boolean {
        val text = field.editText?.text.toString().trim()
        if (text.isEmpty()) {
            view?.onError(field,"Field can't be empty!")
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