package com.example.scame.retroflowmvp.injection

import android.content.Context
import com.example.scame.retroflowmvp.injection.data.DataModule
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module(includes = [(DataModule::class)])
class AppModule(val context: Context) {

    @Provides
    @Singleton
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideSubscribeOn(): SubscribeOn = object : SubscribeOn {
        override fun subscribeOn() = Schedulers.io()
    }

    @Provides
    @Singleton
    fun provideObserveOn(): ObserveOn = object : ObserveOn {
        override fun observeOn() = AndroidSchedulers.mainThread()
    }
}