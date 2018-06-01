package com.example.scame.retroflowmvp.boards.addedit.presenter

import com.example.scame.retroflowmvp.BasePresenter
import com.example.scame.retroflowmvp.boards.BoardApiModel
import com.example.scame.retroflowmvp.boards.addedit.models.BoardDefaultSettings

interface BoardAddEditPresenter<T>: BasePresenter<T> {

    interface BoardAddEditView {

        fun onDefaultBoardSettings(boardDefaultSettings: BoardDefaultSettings)

        fun onProgressChanged(show: Boolean)

        fun onBoardCreated()

        fun onError(throwable: Throwable)
    }

    fun requestDefaultBoardSettings()

    fun createBoard(name: String, sprintStartDate: String, discussionPeriod: Int,
                    sprintDuration: Int, columnNames: List<String>)
}