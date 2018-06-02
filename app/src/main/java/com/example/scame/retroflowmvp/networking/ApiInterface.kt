package com.example.scame.retroflowmvp.networking

import com.example.scame.retroflowmvp.boards.BoardApiModel
import com.example.scame.retroflowmvp.boards.addedit.models.BoardCreateBody
import com.example.scame.retroflowmvp.boards.addedit.models.BoardDefaultSettings
import com.example.scame.retroflowmvp.networking.body.LoginBody
import com.example.scame.retroflowmvp.networking.body.RegistrationBody
import com.example.scame.retroflowmvp.networking.response.RegistrationResponse
import com.example.scame.retroflowmvp.networking.response.TokenModel
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {

    @POST("auth/jwt-obtain/")
    fun login(@Body loginBody: LoginBody): Single<TokenModel>

    @POST("auth/users/")
    fun register(@Body registrationBody: RegistrationBody): Single<RegistrationResponse>

    @POST("auth/users/logout/all/")
    fun logout(@Header("Authorization") bearerToken: String): Completable

    @GET("core/default_settings/")
    fun getBoardDefaultSettings(@Header("Authorization") bearerToken: String): Single<BoardDefaultSettings>

    @POST("core/boards/")
    fun createBoard(@Header("Authorization") bearerToken: String,
                    @Body boardCreateBody: BoardCreateBody): Single<BoardApiModel>

    @GET("core/boards/")
    fun getBoards(@Header("Authorization") bearerToken: String): Single<List<BoardApiModel>>
}