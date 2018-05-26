package com.example.scame.retroflowmvp.boards.view.sprints

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RetroSection(val sectionId: String, val name: String,
                        val actionItems: List<ActionItem>, val open: Boolean): Parcelable

@Parcelize
data class ActionItem(val id: String, val title: String,
                      val description: String, val comments: List<Comment>,
                      val assignee: String?, val canEdit: Boolean, val votes: Int,
                      val canVote: Boolean): Parcelable

@Parcelize
data class Comment(val id: String, val author: String, val text: String): Parcelable