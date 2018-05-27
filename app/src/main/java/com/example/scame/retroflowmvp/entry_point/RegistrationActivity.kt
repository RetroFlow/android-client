package com.example.scame.retroflowmvp.entry_point

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
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


class RegistrationActivity: AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        ButterKnife.bind(this)

        signupButton.setOnClickListener { signUp() }

        loginLink.setOnClickListener { finish() }
    }

    private fun signUp() {
        if (!validate()) {
            onSignupFailed()
            return
        }

        signupButton.isEnabled = false;

        val progressDialog = ProgressDialog(this, R.style.Base_Theme_AppCompat_Dialog)
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Creating Account...")
        progressDialog.show()

        android.os.Handler().postDelayed({
                    onSignupSuccess()
                    // onSignupFailed();
                    progressDialog.dismiss()
                }, 3000)
    }

    fun onSignupSuccess() {
        signupButton.isEnabled = true
        setResult(Activity.RESULT_OK, null)
        finish()
    }

    private fun onSignupFailed() {
        Toast.makeText(baseContext, "Login failed", Toast.LENGTH_LONG).show()
        signupButton.isEnabled = true
    }

    private fun validate(): Boolean {
        var valid = true

        val name = nameText.text.toString()
        val email = emailText.text.toString()
        val password = passwordText.text.toString()

        if (name.isEmpty() || name.length < 3) {
            nameText.error = "at least 3 characters"
            valid = false
        } else {
            nameText.error = null
        }

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