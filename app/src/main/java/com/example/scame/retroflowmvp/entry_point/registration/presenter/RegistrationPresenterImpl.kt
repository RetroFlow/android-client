package com.example.scame.retroflowmvp.entry_point.registration.presenter

import com.example.scame.retroflowmvp.entry_point.EntryRepository
import io.reactivex.disposables.CompositeDisposable

class RegistrationPresenterImpl<T: RegistrationPresenter.RegistrationView>(
        private val entryRepository: EntryRepository
): RegistrationPresenter<T> {

    private var view: T? = null

    private val composite: CompositeDisposable = CompositeDisposable()

    override fun register(password1: String, password2: String, email: String, name: String) {
        if (validate(password1, password1, email, name)) {
            composite.add(entryRepository
                    .register()
                    .doOnSubscribe { view?.onProgressChange(true, "Creating Account...") }
                    .doFinally { view?.onProgressChange(false) }
                    .subscribe(
                            { view?.onSuccess() },
                            { view?.onError(it) }
                    )
            )
        }
    }

    private fun validate(password1: String, password2: String, email: String, name: String): Boolean {
        var valid = true

        if (name.isEmpty() || name.length < 3) {
            view?.onNameValidationError("at least 3 characters")
            valid = false
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view?.onEmailValidationError("enter a valid email address")
            valid = false
        }

        if (password1.isEmpty() || password1.length < 4 || password1.length > 10) {
            view?.onPasswordValidationError("between 4 and 10 alphanumeric characters")
            valid = false
        } else if (password1 != password2) {
            view?.onPasswordValidationError("passwords should match")
            valid = false
        }

        return valid
    }

    override fun toLoginClick() {
        view?.onLoginRedirect()
    }

    override fun subscribe(view: T) {
        this.view = view
    }

    override fun unsubscribe() {
        this.view = null
        composite.clear()
    }
}