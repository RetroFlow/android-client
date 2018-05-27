package com.example.scame.retroflowmvp.entry_point.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import butterknife.ButterKnife
import com.example.scame.retroflowmvp.R
import android.app.ProgressDialog
import android.content.Context
import butterknife.BindView
import butterknife.OnClick
import com.example.scame.retroflowmvp.BottomNavigationActivity
import com.example.scame.retroflowmvp.RetroFlowApp
import com.example.scame.retroflowmvp.entry_point.login.di.LoginModule
import com.example.scame.retroflowmvp.entry_point.login.presenter.LoginPresenter
import com.example.scame.retroflowmvp.entry_point.registration.RegistrationActivity
import javax.inject.Inject


class LoginActivity : AppCompatActivity(), LoginPresenter.LoginView {

    companion object {
        private const val REQUEST_SIGNUP = 0

        fun getIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }

    @BindView(R.id.input_email)
    lateinit var emailText: EditText
    @BindView(R.id.input_password)
    lateinit var passwordText: EditText

    @Inject
    lateinit var presenter: LoginPresenter<LoginPresenter.LoginView>

    private val loginComponent by lazy {
        RetroFlowApp.appComponent.provideLoginComponent(LoginModule())
    }

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        ButterKnife.bind(this)

        setupInjection()
    }

    override fun onStart() {
        super.onStart()
        presenter.subscribe(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unsubscribe()
    }

    override fun onRegistrationRedirect() {
        startActivityForResult(RegistrationActivity.getInstance(this), REQUEST_SIGNUP)
    }

    override fun onProgressChanged(show: Boolean, msg: String) {
        if (show) {
            if (progressDialog == null) {
                progressDialog = ProgressDialog(this, R.style.Theme_AppCompat_DayNight_Dialog)
            }
            progressDialog?.isIndeterminate = true
            progressDialog?.setMessage(msg)
            progressDialog?.show()
        } else {
            progressDialog?.dismiss()
        }
    }

    override fun onSuccess() {
        startActivity(BottomNavigationActivity.getIntent(this))
        finish()
    }

    override fun onError(throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onEmailValidationError(msg: String) {
        emailText.error = msg
    }

    override fun onPasswordValidationError(msg: String) {
        passwordText.error = msg
    }

    private fun setupInjection() {
        loginComponent.inject(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == Activity.RESULT_OK) {
                startActivity(BottomNavigationActivity.getIntent(this))
                this.finish()
            }
        }
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    @OnClick(R.id.link_signup)
    fun onSignUpLinkClick() {
        presenter.openRegistration()
    }

    @OnClick(R.id.btn_login)
    fun onLoginClick() {
        presenter.login(emailText.text.toString(), passwordText.text.toString())
    }
}