package com.example.scame.retroflowmvp.boards.addedit.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
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

data class DeepBoard(@Expose val name: String,
                     @Expose val settings: BoardDefaultSettings,
                     @Expose val status: String,
                     @Expose @SerializedName("created_at")
                     val date: Date,
                     @Expose @SerializedName("team_id")
                     val teamId: String,
                     @Expose val id: Int,
                     @Expose @SerializedName("current_sprint") val nextSprint: SprintEntity,
                     @Expose @SerializedName("previous_sprint") val currSprint: SprintEntity)

@Parcelize
data class SprintEntity(@Expose val id: Int,
                        @Expose @SerializedName("start_date") val startDate: Date,
                        @Expose val duration: Int,
                        @Expose val board: Int,
                        @Expose val columns: List<ColumnEntity>): Parcelable

@Parcelize
data class ColumnEntity(@Expose val id: Int,
                        @Expose val sprint: Int,
                        @Expose val name: String,
                        @Expose val items: List<ItemEntity>): Parcelable

@Parcelize
data class ItemEntity(@Expose val id: Int,
                      @Expose @SerializedName("vote_count") val voteCount: String,
                      @Expose @SerializedName("author_id") val authorId: String,
                      @Expose val heading: String,
                      @Expose val description: String,
                      @Expose val status: String,
                      @Expose val votes: List<VoteEntity>): Parcelable

@Parcelize
data class VoteEntity(@Expose @SerializedName("profile_id") val profileId: Int,
                      @Expose @SerializedName("item_id") val itemId: Int,
                      @Expose @SerializedName("profile_info") val profileInfo: ProfileInfoEntity): Parcelable

@Parcelize
data class ProfileInfoEntity(@Expose val username: String,
                             @Expose @SerializedName("full_name") val fullName: String,
                             @Expose val icon: String,
                             @Expose val pk: Int): Parcelable

data class CommentEntity(@Expose val id: Int?,
                         @Expose val author: Int,
                         @Expose @SerializedName("item_id") val itemId: String,
                         @Expose @SerializedName("author_info") val authorInfo: ProfileInfoEntity,
                         @Expose val text: String)

data class ItemBody(@Expose @SerializedName("column_id") val columnId: Int,
                    @Expose @SerializedName("heading") val heading: String,
                    @Expose val description: String)

data class CommentBody(@Expose val text: String)