package com.example.scame.retroflowmvp.entry_point.login.presenter

import com.example.scame.retroflowmvp.BasePresenter

interface LoginPresenter<T>: BasePresenter<T> {

    interface LoginView {

        fun onRegistrationRedirect()

        fun onProgressChanged(show: Boolean, msg: String = "")

        fun onSuccess()

        fun onError(throwable: Throwable)

        fun onEmailValidationError(msg: String)

        fun onPasswordValidationError(msg: String)
    }

    fun login(email: String, password: String)

    fun openRegistration()
}