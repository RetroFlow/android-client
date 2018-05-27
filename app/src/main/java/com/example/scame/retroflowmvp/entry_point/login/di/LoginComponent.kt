package com.example.scame.retroflowmvp.entry_point.login.di

import com.example.scame.retroflowmvp.entry_point.login.LoginActivity
import com.example.scame.retroflowmvp.injection.PerModule
import dagger.Subcomponent

@PerModule
@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {

    fun inject(loginActivity: LoginActivity)
}