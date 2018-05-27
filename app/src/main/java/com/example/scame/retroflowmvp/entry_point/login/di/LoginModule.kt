package com.example.scame.retroflowmvp.entry_point.login.di

import com.example.scame.retroflowmvp.entry_point.EntryRepository
import com.example.scame.retroflowmvp.entry_point.login.presenter.LoginPresenter
import com.example.scame.retroflowmvp.entry_point.login.presenter.LoginPresenterImpl
import com.example.scame.retroflowmvp.injection.PerModule
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    @PerModule
    fun provideLoginPresenter(entryRepository: EntryRepository):
            LoginPresenter<LoginPresenter.LoginView> =
                LoginPresenterImpl(entryRepository)
}