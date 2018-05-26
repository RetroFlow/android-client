package com.example.scame.retroflowmvp.boards.view.sprints.section.action_item.repository

import com.example.scame.retroflowmvp.boards.view.sprints.ActionItem
import com.example.scame.retroflowmvp.boards.view.sprints.Comment
import io.reactivex.Completable
import io.reactivex.Single

interface ActionItemRepository {

    fun addComment(actionItemId: String, comment: Comment): Completable

    fun getComments(actionItemId: String): Single<List<Comment>>

    fun editActionItem(actionItem: ActionItem): Single<ActionItem>
}