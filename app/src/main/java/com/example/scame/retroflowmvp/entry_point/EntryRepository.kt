package com.example.scame.retroflowmvp.entry_point

import io.reactivex.Completable

interface EntryRepository {

    fun login(email: String, password: String): Completable

    fun register(email: String, password: String, name: String): Completable

    fun logout(): Completable
}