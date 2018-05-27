package com.example.scame.retroflowmvp.entry_point

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import com.example.scame.retroflowmvp.BottomNavigationActivity
import com.example.scame.retroflowmvp.entry_point.login.LoginActivity
import com.example.scame.retroflowmvp.utils.getToken

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (PreferenceManager.getDefaultSharedPreferences(this).getToken() != null) {
            startActivity(BottomNavigationActivity.getIntent(this))
        } else {
            startActivity(LoginActivity.getIntent(this))
        }

        finish()
    }
}