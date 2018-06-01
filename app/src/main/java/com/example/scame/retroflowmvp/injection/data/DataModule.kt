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

    companion object {
        const val BASE_URL = "https://retroflow.herokuapp.com/"
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context) = PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun provideBoardsRepository(): BoardsRepository = BoardsRepositoryImpl()

    @Provides
    @Singleton
    fun provideRetroItemsRepository(): RetroItemsRepository = RetroItemsRepositoryImpl()

    @Provides
    @Singleton
    fun provideActionItemRepository(): ActionItemRepository = ActionItemRepositoryImpl()

    @Provides
    @Singleton
    fun provideEntryRepository(apiInterface: ApiInterface, sp: SharedPreferences,
                               subscribeOn: SubscribeOn, observeOn: ObserveOn): EntryRepository =
            EntryRepositoryImpl(apiInterface, sp, subscribeOn, observeOn)

    @Provides
    @Singleton
    fun provideProfileRepository(sp: SharedPreferences): ProfileRepository = ProfileRepositoryImpl(sp)
}