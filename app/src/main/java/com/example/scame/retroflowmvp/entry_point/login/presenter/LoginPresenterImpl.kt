package com.example.scame.retroflowmvp.entry_point.login.presenter

import com.example.scame.retroflowmvp.entry_point.EntryRepository
import io.reactivex.disposables.CompositeDisposable

class LoginPresenterImpl<T: LoginPresenter.LoginView>(
        private val entryRepository: EntryRepository
): LoginPresenter<T> {

    private var view: T? = null

    private val composite: CompositeDisposable = CompositeDisposable()

    override fun login(email: String, password: String) {
        if (!validate(email, password)) {
            return
        }

        composite.add(entryRepository
                .login(email, password)
                .doOnSubscribe { view?.onProgressChanged(true, "Authenticating...") }
                .doFinally { view?.onProgressChanged(false) }
                .subscribe(
                        { view?.onSuccess() },
                        { view?.onError(it) }
                )
        )
    }

    private fun validate(email: String, password: String): Boolean {
        var valid = true

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view?.onEmailValidationError("enter a valid email address")
            valid = false
        }

        if (password.isEmpty() || password.length < 4 || password.length > 10) {
            view?.onPasswordValidationError("between 4 and 10 alphanumeric characters")
            valid = false
        }

        return valid
    }

    override fun openRegistration() {
        view?.onRegistrationRedirect()
    }

    override fun subscribe(view: T) {
        this.view = view
    }

    override fun unsubscribe() {
        this.view = null
        this.composite.clear()
    }
}