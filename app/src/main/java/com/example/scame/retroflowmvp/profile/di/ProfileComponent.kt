package com.example.scame.retroflowmvp.profile.di

import com.example.scame.retroflowmvp.injection.PerModule
import com.example.scame.retroflowmvp.profile.ProfileFragment
import dagger.Subcomponent

@PerModule
@Subcomponent(modules = [ProfileModule::class])
interface ProfileComponent {

    fun inject(profileFragment: ProfileFragment)
}