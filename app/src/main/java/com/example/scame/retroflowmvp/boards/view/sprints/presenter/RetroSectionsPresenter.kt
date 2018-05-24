package com.example.scame.retroflowmvp.boards.view.sprints.presenter

import com.example.scame.retroflowmvp.BasePresenter
import com.example.scame.retroflowmvp.boards.view.sprints.RetroSection

interface RetroSectionsPresenter<T>: BasePresenter<T> {

    interface RetroSectionsView {

        fun onRetroSections(retroSections: List<RetroSection>)

        fun onProgressChanged(show: Boolean)

        fun onError(throwable: Throwable)
    }

    fun requestRetroSections(boardId: String)
}

