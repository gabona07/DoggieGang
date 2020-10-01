package com.codecool.doggiegang.util

import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

interface RegistrationContract {

    interface RegistrationView {
        fun onError(field: TextInputLayout, message: String)
    }

    interface RegistrationValidation {
        fun validateInputs(usernameField : TextInputLayout, emailField  : TextInputLayout, passwordField : TextInputLayout, dogNameField  : TextInputLayout, introductionField  : TextInputLayout, locationField  : TextInputLayout, successText : TextView)
        fun onAttach(view: RegistrationView)
        fun onDetach()
    }
}