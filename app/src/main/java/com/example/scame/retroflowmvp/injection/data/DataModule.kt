package com.example.scame.retroflowmvp.injection.data

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.scame.retroflowmvp.boards.BoardsRepository
import com.example.scame.retroflowmvp.boards.BoardsRepositoryImpl
import com.example.scame.retroflowmvp.boards.view.sprints.repository.RetroItemsRepository
import com.example.scame.retroflowmvp.boards.view.sprints.repository.RetroItemsRepositoryImpl
import com.example.scame.retroflowmvp.boards.view.sprints.section.action_item.repository.ActionItemRepository
import com.example.scame.retroflowmvp.boards.view.sprints.section.action_item.repository.ActionItemRepositoryImpl
import com.example.scame.retroflowmvp.entry_point.EntryRepository
import com.example.scame.retroflowmvp.entry_point.EntryRepositoryImpl
import com.example.scame.retroflowmvp.injection.ObserveOn
import com.example.scame.retroflowmvp.injection.SubscribeOn
import com.example.scame.retroflowmvp.networking.ApiInterface
import com.example.scame.retroflowmvp.profile.profile.ProfileRepository
import com.example.scame.retroflowmvp.profile.profile.ProfileRepositoryImpl
import dagger.Module
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class DataModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context) = PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun provideBoardsRepository(sp: SharedPreferences, apiInterface: ApiInterface,
                                subscribeOn: SubscribeOn, observeOn: ObserveOn): BoardsRepository =
            BoardsRepositoryImpl(apiInterface, sp, subscribeOn, observeOn)

    @Provides
    @Singleton
    fun provideRetroItemsRepository(): RetroItemsRepository = RetroItemsRepositoryImpl()

    @Provides
    @Singleton
    fun provideActionItemRepository(sp: SharedPreferences, apiInterface: ApiInterface,
                                    subscribeOn: SubscribeOn, observeOn: ObserveOn): ActionItemRepository =
            ActionItemRepositoryImpl(apiInterface, sp, subscribeOn, observeOn)

    @Provides
    @Singleton
    fun provideEntryRepository(apiInterface: ApiInterface, sp: SharedPreferences,
                               subscribeOn: SubscribeOn, observeOn: ObserveOn): EntryRepository =
            EntryRepositoryImpl(apiInterface, sp, subscribeOn, observeOn)

    @Provides
    @Singleton
    fun provideProfileRepository(sp: SharedPreferences, apiInterface: ApiInterface,
                                 subscribeOn: SubscribeOn, observeOn: ObserveOn):
            ProfileRepository = ProfileRepositoryImpl(sp, apiInterface, subscribeOn, observeOn)
}