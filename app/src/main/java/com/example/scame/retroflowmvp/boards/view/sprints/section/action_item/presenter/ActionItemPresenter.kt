package com.example.scame.retroflowmvp.boards.view.sprints.section.action_item.presenter

import com.example.scame.retroflowmvp.BasePresenter
import com.example.scame.retroflowmvp.boards.view.sprints.ActionItem
import com.example.scame.retroflowmvp.boards.view.sprints.Comment

interface ActionItemPresenter<T>: BasePresenter<T> {

    interface ActionItemView {

        fun onRenderActionItem(actionItem: ActionItem)

        fun onCommentValidationFailed()

        fun onActionItemEdit(edited: ActionItem)

        fun onActionItemComments(comments: List<Comment>)

        fun clearCommentInput()

        fun onError(throwable: Throwable)

        fun onActionItemProgressChanged(show: Boolean)

        fun onCommentsProgressChanged(show: Boolean)
    }

    fun setModel(actionItem: ActionItem)

    fun requestComments(actionItemId: String)

    fun addComment(actionItem: ActionItem, commentText: String)

    fun editActionItem(actionItem: ActionItem)
}