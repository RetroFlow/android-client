package com.example.scame.retroflowmvp.entry_point.registration.di

import com.example.scame.retroflowmvp.entry_point.registration.RegistrationActivity
import com.example.scame.retroflowmvp.injection.PerModule
import dagger.Subcomponent

@PerModule
@Subcomponent(modules = [RegistrationModule::class])
interface RegistrationComponent {

    fun inject(registrationActivity: RegistrationActivity)
}