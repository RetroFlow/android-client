package com.example.scame.retroflowmvp.boards.view.sprints.section.action_item.presenter

import com.example.scame.retroflowmvp.BasePresenter
import com.example.scame.retroflowmvp.boards.addedit.models.CommentEntity
import com.example.scame.retroflowmvp.boards.addedit.models.ItemEntity
import com.example.scame.retroflowmvp.boards.view.sprints.ActionItem
import com.example.scame.retroflowmvp.boards.view.sprints.Comment

interface ActionItemPresenter<T>: BasePresenter<T> {

    interface ActionItemView {

        fun onRenderActionItem(actionItem: ItemEntity)

        fun onCommentValidationFailed()

        fun onActionItemEdit(edited: ItemEntity)

        fun onActionItemComments(comments: List<CommentEntity>)

        fun clearCommentInput()

        fun onError(throwable: Throwable)

        fun onActionItemProgressChanged(show: Boolean)

        fun onCommentsProgressChanged(show: Boolean)
    }

    fun setModel(actionItem: ItemEntity)

    fun requestComments(actionItemId: Int)

    fun addComment(item: ItemEntity, commentText: String)

    fun editActionItem(actionItem: ActionItem)
}