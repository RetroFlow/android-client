package com.example.scame.retroflowmvp.profile.profile

import com.example.scame.retroflowmvp.profile.ProfileModel
import io.reactivex.Single

interface ProfileRepository {

    fun requestProfile(): Single<ProfileModel>

    fun updateProfile(profile: ProfileModel): Single<ProfileModel>
}