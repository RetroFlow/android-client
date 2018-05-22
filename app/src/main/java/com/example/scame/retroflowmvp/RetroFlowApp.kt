package com.example.scame.retroflowmvp

import android.app.Application
import com.example.scame.retroflowmvp.injection.AppComponent
import com.example.scame.retroflowmvp.injection.AppModule
import com.example.scame.retroflowmvp.injection.DaggerAppComponent

class RetroFlowApp: Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}