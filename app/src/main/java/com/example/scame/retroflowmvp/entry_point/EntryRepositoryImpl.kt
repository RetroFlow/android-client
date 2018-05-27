package com.example.scame.retroflowmvp.entry_point

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class EntryRepositoryImpl: EntryRepository {

    override fun login(email: String, password: String): Single<Boolean> {
        return Single.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).map { true }
    }

    override fun register(): Single<Boolean> {
        return Single.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).map { true }
    }

    override fun logout(): Single<Boolean> {
        return Single.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).map { true }
    }
}