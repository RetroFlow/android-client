package com.example.scame.retroflowmvp.networking

import com.example.scame.retroflowmvp.networking.body.LoginBody
import com.example.scame.retroflowmvp.networking.body.RegistrationBody
import com.example.scame.retroflowmvp.networking.response.RegistrationResponse
import com.example.scame.retroflowmvp.networking.response.TokenModel
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {

    @POST("auth/jwt-obtain/")
    fun login(@Body loginBody: LoginBody): Single<TokenModel>

    @POST("auth/users/")
    fun register(@Body registrationBody: RegistrationBody): Single<RegistrationResponse>

    @POST("auth/users/logout/all")
    fun logout(@Header("Bearer") bearerToken: String): Completable
}