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
import com.example.scame.retroflowmvp.BottomNavigationActivity
import com.example.scame.retroflowmvp.entry_point.registration.RegistrationActivity


class LoginActivity : AppCompatActivity() {

    companion object {
        private val REQUEST_SIGNUP = 0

        fun getIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }

    @BindView(R.id.input_email)
    lateinit var emailText: EditText
    @BindView(R.id.input_password)
    lateinit var passwordText: EditText
    @BindView(R.id.btn_login)
    lateinit var loginButton: Button
    @BindView(R.id.link_signup)
    lateinit var signupLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        ButterKnife.bind(this)

        loginButton.setOnClickListener { login() }

        signupLink.setOnClickListener {
            startActivityForResult(RegistrationActivity.getInstance(this), REQUEST_SIGNUP)
        }
    }

    private fun login() {
        if (!validate()) {
            onLoginFailed()
            return
        }

        loginButton.isEnabled = false

        val progressDialog = ProgressDialog(this, R.style.Base_Theme_AppCompat_Dialog)
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Authenticating...")
        progressDialog.show()

        val email = emailText.text.toString()
        val password = passwordText.text.toString()


        android.os.Handler().postDelayed(
                {
                    // On complete call either onLoginSuccess or onLoginFailed
                    onLoginSuccess()
                    // onLoginFailed();
                    progressDialog.dismiss()
                }, 3000)
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

    private fun onLoginSuccess() {
        loginButton.isEnabled = true
        startActivity(BottomNavigationActivity.getIntent(this))
        finish()
    }

    private fun onLoginFailed() {
        Toast.makeText(baseContext, "Login failed", Toast.LENGTH_LONG).show()
        loginButton.isEnabled = true
    }

    private fun validate(): Boolean {
        var valid = true

        val email = emailText.text.toString()
        val password = passwordText.text.toString()

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.error = "enter a valid email address"
            valid = false
        } else {
            emailText.error = null
        }

        if (password.isEmpty() || password.length < 4 || password.length > 10) {
            passwordText.error = "between 4 and 10 alphanumeric characters"
            valid = false
        } else {
            passwordText.error = null
        }

        return valid
    }
}