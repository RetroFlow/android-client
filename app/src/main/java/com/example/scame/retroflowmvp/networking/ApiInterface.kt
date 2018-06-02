package com.example.scame.retroflowmvp.networking

import com.example.scame.retroflowmvp.boards.BoardApiModel
import com.example.scame.retroflowmvp.boards.addedit.models.*
import com.example.scame.retroflowmvp.networking.body.LoginBody
import com.example.scame.retroflowmvp.networking.body.RegistrationBody
import com.example.scame.retroflowmvp.networking.response.RegistrationResponse
import com.example.scame.retroflowmvp.networking.response.TokenModel
import com.example.scame.retroflowmvp.profile.ProfileEntity
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

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

    @GET("core/board/{id}/")
    fun getDeepBoard(@Header("Authorization") bearerToken: String,
                     @Path("id") id: Int): Single<DeepBoard>

    @GET("core/boards/{id}/start_new_sprint/")
    fun startNewSprints(@Header("Authorization") bearerToken: String,
                        @Path("id") id: Int): Completable

    @GET("core/items/{item_pk}/comments/")
    fun getCommnets(@Header("Authorization") bearerToken: String,
                    @Path("item_pk") itemPk: Int): Single<List<CommentEntity>>

    @POST("core/items/{item_pk}/comments/")
    fun postComment(@Header("Authorization") bearerToken: String,
                    @Path("item_pk") itemPk: Int,
                    @Body commentEntity: CommentBody): Single<CommentEntity>

    @GET("core/profile/")
    fun requestProfile(@Header("Authorization") bearerToken: String): Single<ProfileEntity>
}