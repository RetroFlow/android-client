package com.example.scame.retroflowmvp.entry_point

import io.reactivex.Single

interface EntryRepository {

    fun login(): Single<Boolean>

    fun register(): Single<Boolean>

    fun logout(): Single<Boolean>
}