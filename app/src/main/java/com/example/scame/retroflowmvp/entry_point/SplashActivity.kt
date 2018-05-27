package com.example.scame.retroflowmvp.entry_point

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.scame.retroflowmvp.entry_point.login.LoginActivity

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(LoginActivity.getIntent(this))
        finish()
    }
}