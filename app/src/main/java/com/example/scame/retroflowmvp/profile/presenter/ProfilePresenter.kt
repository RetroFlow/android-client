package com.example.scame.retroflowmvp.profile.presenter

import com.example.scame.retroflowmvp.BasePresenter
import com.example.scame.retroflowmvp.profile.ProfileEntity
import com.example.scame.retroflowmvp.profile.ProfileModel

interface ProfilePresenter<T>: BasePresenter<T> {

    interface ProfileView {

        fun onProfile(profile: ProfileEntity)

        fun onLogout()

        fun onError(throwable: Throwable)

        fun onProgressChanged(show: Boolean)
    }

    fun requestProfileInfo()

    fun requestLogout()
}