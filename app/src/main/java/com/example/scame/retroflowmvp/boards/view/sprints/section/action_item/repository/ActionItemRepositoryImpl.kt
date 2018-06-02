package com.example.scame.retroflowmvp.boards.view.sprints.section.action_item.repository

import android.content.SharedPreferences
import com.example.scame.retroflowmvp.boards.addedit.models.CommentBody
import com.example.scame.retroflowmvp.boards.addedit.models.CommentEntity
import com.example.scame.retroflowmvp.boards.view.sprints.ActionItem
import com.example.scame.retroflowmvp.boards.view.sprints.Comment
import com.example.scame.retroflowmvp.injection.ObserveOn
import com.example.scame.retroflowmvp.injection.SubscribeOn
import com.example.scame.retroflowmvp.networking.ApiInterface
import com.example.scame.retroflowmvp.utils.getToken
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class ActionItemRepositoryImpl(private val apiInterface: ApiInterface,
                               private val sp: SharedPreferences,
                               private val subscribeOn: SubscribeOn,
                               private val observeOn: ObserveOn): ActionItemRepository {

    override fun addComment(actionItemId: Int, comment: CommentBody): Completable {
        return apiInterface
                .postComment("Bearer " + requireNotNull(sp.getToken()), actionItemId, comment)
                .subscribeOn(subscribeOn.subscribeOn())
                .observeOn(observeOn.observeOn())
                .toCompletable()
    }

    override fun getComments(actionItemId: Int): Single<List<CommentEntity>> {
        return apiInterface
                .getCommnets("Bearer " + requireNotNull(sp.getToken()), actionItemId)
                .subscribeOn(subscribeOn.subscribeOn())
                .observeOn(observeOn.observeOn())
    }

    override fun editActionItem(actionItem: ActionItem): Single<ActionItem> {
        return Single
                .timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .map { actionItem }
    }
}