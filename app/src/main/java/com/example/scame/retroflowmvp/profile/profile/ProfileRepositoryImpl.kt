package com.example.scame.retroflowmvp.profile.profile

import android.content.SharedPreferences
import com.example.scame.retroflowmvp.profile.ProfileModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class ProfileRepositoryImpl(private val sp: SharedPreferences): ProfileRepository {

    override fun requestProfile(): Single<ProfileModel> {
        return Single
                .timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .map { ProfileModel("Slava", "vs.petrochenko@gmail.com") }
    }

    override fun updateProfile(profile: ProfileModel): Single<ProfileModel> {
        return Single
                .timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .map { profile }
    }
}