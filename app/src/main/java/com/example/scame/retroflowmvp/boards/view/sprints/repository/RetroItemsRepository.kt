package com.example.scame.retroflowmvp.boards.view.sprints.repository

import com.example.scame.retroflowmvp.boards.view.sprints.RetroSection
import io.reactivex.Completable
import io.reactivex.Single

interface RetroItemsRepository {

    fun getRetroSectionsForBoard(boardId: String): Single<List<RetroSection>>

    fun createRetroSectionForBoard(retroSection: RetroSection): Completable

    fun editRetroSectionForBoard(boardId: String): Completable

    fun removeRetroSectionForBoard(boardId: String): Completable
}