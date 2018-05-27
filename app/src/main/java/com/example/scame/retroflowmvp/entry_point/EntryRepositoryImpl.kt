package com.example.scame.retroflowmvp.entry_point

import android.content.SharedPreferences
import com.example.scame.retroflowmvp.utils.clearToken
import com.example.scame.retroflowmvp.utils.saveToken
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class EntryRepositoryImpl(private val sp: SharedPreferences): EntryRepository {

    override fun login(email: String, password: String): Single<Boolean> {
        return Single.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .doAfterSuccess { sp.saveToken(it.toString()) }
                .map { true }
    }

    override fun register(): Single<Boolean> {
        return Single.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .doAfterSuccess { sp.saveToken(it.toString()) }
                .map { true }
    }

    override fun logout(): Single<Boolean> {
        return Single.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .doAfterSuccess { sp.clearToken() }
                .map { true }
    }
}