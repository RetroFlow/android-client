package com.example.scame.retroflowmvp.utils

import android.content.SharedPreferences

private const val OAUTH_TOKEN_KEY = "oauth_token_key"

fun SharedPreferences.setToken(token: String) = set(OAUTH_TOKEN_KEY, token)

fun SharedPreferences.getToken(): String? = getString(OAUTH_TOKEN_KEY, null)

fun SharedPreferences.clearToken() = edit().clear().apply()

private fun <T> SharedPreferences.set(key: String, data: T) {
    val editable = this.edit()

    when (data) {
        is Boolean -> { editable.putBoolean(key, data) }
        is Int -> { editable.putInt(key, data) }
        is Long -> { editable.putLong(key, data) }
        is Float -> { editable.putFloat(key, data) }
        is String -> { editable.putString(key, data) }
        else -> throw IllegalArgumentException("unsupported data type: ${data.toString()}")
    }

    editable.apply()
}
