package com.example.scame.retroflowmvp.entry_point.registration.presenter

import com.example.scame.retroflowmvp.BasePresenter

interface RegistrationPresenter<T>: BasePresenter<T> {

    interface RegistrationView {

        fun onPasswordValidationError(msg: String)

        fun onEmailValidationError(msg: String)

        fun onNameValidationError(msg: String)

        fun onError(throwable: Throwable)

        fun onSuccess()

        fun onLoginRedirect()

        fun onProgressChange(show: Boolean, msg: String = "")
    }

    fun register(password1: String, password2: String, email: String, name: String)

    fun toLoginClick()
}