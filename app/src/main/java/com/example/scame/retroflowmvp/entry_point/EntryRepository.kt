package com.example.scame.retroflowmvp.entry_point

import io.reactivex.Single

interface EntryRepository {

    fun login(email: String, password: String): Single<Boolean>

    fun register(): Single<Boolean>

    fun logout(): Single<Boolean>
}