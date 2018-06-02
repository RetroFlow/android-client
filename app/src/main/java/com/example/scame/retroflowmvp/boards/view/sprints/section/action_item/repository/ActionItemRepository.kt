package com.example.scame.retroflowmvp.boards.view.sprints.section.action_item.repository

import com.example.scame.retroflowmvp.boards.addedit.models.CommentBody
import com.example.scame.retroflowmvp.boards.addedit.models.CommentEntity
import com.example.scame.retroflowmvp.boards.view.sprints.ActionItem
import com.example.scame.retroflowmvp.boards.view.sprints.Comment
import io.reactivex.Completable
import io.reactivex.Single

interface ActionItemRepository {

    fun addComment(actionItemId: Int, comment: CommentBody): Completable

    fun getComments(actionItemId: Int): Single<List<CommentEntity>>

    fun editActionItem(actionItem: ActionItem): Single<ActionItem>
}