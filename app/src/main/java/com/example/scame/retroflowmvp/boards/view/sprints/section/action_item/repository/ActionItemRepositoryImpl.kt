package com.example.scame.retroflowmvp.boards.view.sprints.section.action_item.repository

import com.example.scame.retroflowmvp.boards.view.sprints.ActionItem
import com.example.scame.retroflowmvp.boards.view.sprints.Comment
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class ActionItemRepositoryImpl: ActionItemRepository {

    override fun addComment(actionItemId: String, comment: Comment): Completable {
        return Completable.complete()
    }

    override fun getComments(actionItemId: String): Single<List<Comment>> {
        return getCommentsSingleList()
    }

    private fun getCommentsSingleList() = Single
            .timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .map { listOf(
                    Comment("1", "Slava", "Comment 1"),
                    Comment("2", "Masha", "Comment 2"),
                    Comment("3", "Nastya", "Comment 3")
            )}

    override fun editActionItem(actionItem: ActionItem): Single<ActionItem> {
        return Single
                .timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .map { actionItem }
    }
}