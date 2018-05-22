package com.example.scame.retroflowmvp

import android.app.Application
import com.example.scame.retroflowmvp.injection.AppModule
import com.example.scame.retroflowmvp.injection.DaggerAppComponent

class RetroFlowApp: Application() {

    val appComponent by lazy { DaggerAppComponent.builder().appModule(AppModule(this)) }

    override fun onCreate() {
        super.onCreate()
    }
}