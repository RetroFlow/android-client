package com.example.scame.retroflowmvp.networking.body

import com.google.gson.annotations.Expose

data class LoginBody(@Expose val email: String,
                     @Expose val password: String)

data class RegistrationBody(@Expose val email: String,
                            @Expose val password: String,
                            @Expose val username: String)