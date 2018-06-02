package com.example.scame.retroflowmvp.boards

import com.example.scame.retroflowmvp.boards.addedit.models.BoardDefaultSettings
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class BoardRawModel(val id: String, val name: String, val stage: String, val canEdit: Boolean)

data class BoardApiModel(@Expose val name: String,
                         @Expose val settings: BoardDefaultSettings,
                         @Expose val status: String,
                         @Expose @SerializedName("created_at")
                         val date: Date,
                         @Expose @SerializedName("team_id")
                         val teamId: String,
                         @Expose val id: Int)
