package com.example.scame.retroflowmvp.entry_point.registration

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import butterknife.ButterKnife
import com.example.scame.retroflowmvp.R
import android.widget.TextView
import android.widget.EditText
import butterknife.BindView
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.widget.Toast
import butterknife.OnClick
import com.example.scame.retroflowmvp.RetroFlowApp
import com.example.scame.retroflowmvp.entry_point.registration.di.RegistrationComponent
import com.example.scame.retroflowmvp.entry_point.registration.di.RegistrationModule
import com.example.scame.retroflowmvp.entry_point.registration.presenter.RegistrationPresenter
import javax.inject.Inject


class RegistrationActivity: AppCompatActivity(), RegistrationPresenter.RegistrationView {

    companion object {

        fun getInstance(context: Context) = Intent(context, RegistrationActivity::class.java)
    }

    @BindView(R.id.input_name)
    lateinit var nameText: EditText
    @BindView(R.id.input_email)
    lateinit var emailText: EditText
    @BindView(R.id.input_password)
    lateinit var passwordText: EditText
    @BindView(R.id.btn_signup)
    lateinit var signupButton: Button
    @BindView(R.id.link_login)
    lateinit var loginLink: TextView

    @Inject
    lateinit var registrationPresenter: RegistrationPresenter<RegistrationPresenter.RegistrationView>

    private var progressDialog: ProgressDialog? = null

    private val registrationComponent: RegistrationComponent by lazy {
        RetroFlowApp.appComponent.provideRegistrationComponent(RegistrationModule())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        ButterKnife.bind(this)

        setupInjection()
    }

    override fun onStart() {
        super.onStart()
        registrationPresenter.subscribe(this)
    }

    override fun onStop() {
        super.onStop()
        registrationPresenter.unsubscribe()
    }

    private fun setupInjection() {
        registrationComponent.inject(this)
    }

    private fun signUp() {
        registrationPresenter.register(
                passwordText.text.toString(),
                passwordText.text.toString(),
                emailText.text.toString(),
                nameText.text.toString()
        )
    }

    override fun onPasswordValidationError(msg: String) {
        passwordText.error = msg
    }

    override fun onEmailValidationError(msg: String) {
        emailText.error = msg
    }

    override fun onNameValidationError(msg: String) {
        nameText.error = msg
    }

    override fun onError(throwable: Throwable) {
    }

    override fun onSuccess() {
        setResult(Activity.RESULT_OK, null)
        finish()
    }

    override fun onLoginRedirect() {
        finish()
    }

    override fun onProgressChange(show: Boolean, msg: String) {
        if (show) {
            if (progressDialog == null) {
                progressDialog = ProgressDialog(this, R.style.Theme_AppCompat_DayNight_Dialog)
            }

            progressDialog?.let {
                it.isIndeterminate = true
                it.setMessage(msg)
                it.show()
            }
        } else {
            progressDialog?.dismiss()
        }
    }

    @OnClick(R.id.btn_signup)
    fun onSignUpClick() {
        signUp()
    }

    @OnClick(R.id.link_login)
    fun onLoginLinkClick() {
        registrationPresenter.toLoginClick()
    }
}