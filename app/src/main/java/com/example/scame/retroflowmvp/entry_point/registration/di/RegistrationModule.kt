package com.example.scame.retroflowmvp.entry_point.registration.di

import com.example.scame.retroflowmvp.entry_point.EntryRepository
import com.example.scame.retroflowmvp.entry_point.registration.presenter.RegistrationPresenter
import com.example.scame.retroflowmvp.entry_point.registration.presenter.RegistrationPresenterImpl
import com.example.scame.retroflowmvp.injection.PerModule
import dagger.Module
import dagger.Provides

@Module
class RegistrationModule {

    @Provides
    @PerModule
    fun provideRegistrationPresenter(entryRepository: EntryRepository):
            RegistrationPresenter<RegistrationPresenter.RegistrationView> =
                RegistrationPresenterImpl(entryRepository)
}