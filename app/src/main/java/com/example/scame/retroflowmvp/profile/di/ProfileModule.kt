package com.example.scame.retroflowmvp.profile.di

import com.example.scame.retroflowmvp.entry_point.EntryRepository
import com.example.scame.retroflowmvp.injection.PerModule
import com.example.scame.retroflowmvp.profile.presenter.ProfilePresenter
import com.example.scame.retroflowmvp.profile.presenter.ProfilePresenterImpl
import com.example.scame.retroflowmvp.profile.profile.ProfileRepository
import dagger.Module
import dagger.Provides

@Module
class ProfileModule {

    @Provides
    @PerModule
    fun provideProfilePresenter(entryRepository: EntryRepository, profileRepository: ProfileRepository):
            ProfilePresenter<ProfilePresenter.ProfileView> = ProfilePresenterImpl(entryRepository, profileRepository)
}