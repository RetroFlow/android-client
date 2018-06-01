package com.example.scame.retroflowmvp.entry_point

import android.content.SharedPreferences
import com.example.scame.retroflowmvp.injection.ObserveOn
import com.example.scame.retroflowmvp.injection.SubscribeOn
import com.example.scame.retroflowmvp.networking.ApiInterface
import com.example.scame.retroflowmvp.networking.body.LoginBody
import com.example.scame.retroflowmvp.networking.body.RegistrationBody
import com.example.scame.retroflowmvp.utils.clearToken
import com.example.scame.retroflowmvp.utils.getToken
import com.example.scame.retroflowmvp.utils.setToken
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class EntryRepositoryImpl(private val apiInterface: ApiInterface,
                          private val sp: SharedPreferences,
                          private val subscribeOn: SubscribeOn,
                          private val observeOn: ObserveOn): EntryRepository {

    override fun login(email: String, password: String): Completable {
        return apiInterface.login(LoginBody(email, password))
                .subscribeOn(subscribeOn.subscribeOn())
                .observeOn(observeOn.observeOn())
                .doAfterSuccess { sp.setToken(it.token) }
                .toCompletable()
    }

    override fun register(email: String, password: String, name: String): Completable {
        return apiInterface
                .register(RegistrationBody(email, password, name))
                .subscribeOn(subscribeOn.subscribeOn())
                .observeOn(observeOn.observeOn())
                .toCompletable().andThen(login(email, password))
    }

    override fun logout(): Completable {
        return apiInterface.logout("Bearer " + requireNotNull(sp.getToken()))
                .subscribeOn(subscribeOn.subscribeOn())
                .observeOn(observeOn.observeOn())
    }
}