package com.example.scame.retroflowmvp.boards.addedit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class BoardDefaultSettings(@Expose @SerializedName("sprint_start_date")
                                val sprintStartDate: Date,
                                @Expose @SerializedName("discussion_period")
                                val discussionPeriod: Int,
                                @Expose @SerializedName("icon")
                                val icon: String,
                                @Expose @SerializedName("sprint_duration")
                                val sprintDuration: Int,
                                @Expose @SerializedName("column_names")
                                val columnNames: List<ColumnName>)

data class ColumnName(@Expose val name: String)

data class BoardCreateBody(@Expose val name: String,
                           @Expose val settings: BoardDefaultSettings)