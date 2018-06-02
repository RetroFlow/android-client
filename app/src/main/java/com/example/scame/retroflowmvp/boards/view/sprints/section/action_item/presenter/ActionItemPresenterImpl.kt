package com.example.scame.retroflowmvp.boards.view.sprints.section.action_item.presenter

import android.util.Log
import com.example.scame.retroflowmvp.boards.addedit.models.CommentBody
import com.example.scame.retroflowmvp.boards.addedit.models.CommentEntity
import com.example.scame.retroflowmvp.boards.addedit.models.ItemEntity
import com.example.scame.retroflowmvp.boards.view.sprints.ActionItem
import com.example.scame.retroflowmvp.boards.view.sprints.Comment
import com.example.scame.retroflowmvp.boards.view.sprints.section.action_item.repository.ActionItemRepository
import io.reactivex.disposables.CompositeDisposable

class ActionItemPresenterImpl<T : ActionItemPresenter.ActionItemView>(
        private val actionItemRepository: ActionItemRepository
) : ActionItemPresenter<T> {

    private var view: T? = null

    private val composite = CompositeDisposable()

    override fun requestComments(actionItemId: Int) {
        composite.add(actionItemRepository
                .getComments(actionItemId)
                .doOnSubscribe { view?.onCommentsProgressChanged(true) }
                .doFinally { view?.onCommentsProgressChanged(false) }
                .subscribe(
                        { view?.onActionItemComments(it) },
                        { view?.onError(it) }
                )
        )
    }

    override fun setModel(actionItem: ItemEntity) {
        view?.onRenderActionItem(actionItem)
    }

    override fun addComment(item: ItemEntity, commentText: String) {
        if (!isCommentValid(commentText)) {
            view?.onCommentValidationFailed()
            return
        }

        composite.add(actionItemRepository
                .addComment(item.id, CommentBody(commentText))
                .andThen(actionItemRepository.getComments(item.id))
                .doOnSubscribe { view?.onCommentsProgressChanged(true) }
                .doFinally { view?.onCommentsProgressChanged(false) }
                .doOnSuccess { view?.clearCommentInput() }
                .subscribe(
                        { view?.onActionItemComments(it) },
                        { view?.onError(it) }
                )
        )
    }

    private fun isCommentValid(commentText: String) = commentText.isNotBlank()

    override fun editActionItem(actionItem: ActionItem) {
        /*composite.add(actionItemRepository
                .editActionItem(actionItem)
                .doOnSubscribe { view?.onActionItemProgressChanged(true) }
                .doFinally { view?.onActionItemProgressChanged(false) }
                .subscribe(
                        { view?.onActionItemEdit(it) },
                        { view?.onError(it) }
                )
        )*/
    }

    override fun subscribe(view: T) {
        this.view = view
    }

    override fun unsubscribe() {
        this.view = null
        this.composite.clear()
    }
}