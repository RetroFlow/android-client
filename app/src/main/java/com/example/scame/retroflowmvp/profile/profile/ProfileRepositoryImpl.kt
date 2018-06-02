package com.example.scame.retroflowmvp.profile.profile

import android.content.SharedPreferences
import com.example.scame.retroflowmvp.injection.ObserveOn
import com.example.scame.retroflowmvp.injection.SubscribeOn
import com.example.scame.retroflowmvp.networking.ApiInterface
import com.example.scame.retroflowmvp.profile.ProfileEntity
import com.example.scame.retroflowmvp.profile.ProfileModel
import com.example.scame.retroflowmvp.utils.getToken
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class ProfileRepositoryImpl(private val sp: SharedPreferences,
                            private val apiInterface: ApiInterface,
                            private val subscribeOn: SubscribeOn,
                            private val observeOn: ObserveOn): ProfileRepository {

    override fun requestProfile(): Single<ProfileEntity> {
        return apiInterface
                .requestProfile("Bearer " + requireNotNull(sp.getToken()))
                .subscribeOn(subscribeOn.subscribeOn())
                .observeOn(observeOn.observeOn())
    }

    override fun updateProfile(profile: ProfileModel): Single<ProfileModel> {
        return Single
                .timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .map { profile }
    }
}