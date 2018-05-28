package com.example.scame.retroflowmvp.networking.response

import com.google.gson.annotations.Expose

data class TokenModel(@Expose val token: String)

data class RegistrationResponse(@Expose val email: String,
                                @Expose val token: String,
                                @Expose val username: String)