package com.example.scame.retroflowmvp.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileEntity(@Expose @SerializedName("public_info") val publicInfo: PublicInfoEntity,
                         @Expose @SerializedName("full_name") val fullName: String,
                         @Expose @SerializedName("username") val username: String,
                         @Expose @SerializedName("icon") val icon: String,
                         @Expose @SerializedName("last_name") val lastName: String,
                         @Expose @SerializedName("first_name") val firstName: String,
                         @Expose val id: Int)

data class PublicInfoEntity(@Expose val id: Int,
                            @Expose val email: String,
                            @Expose @SerializedName("phone_number") val phoneNumber: String,
                            @Expose @SerializedName("additional_info") val additionalInfo: String)

data class ProfileModel(val name: String, val email: String)

data class TokenModel(val token: String)