package com.example.scame.retroflowmvp.boards.presenter

import com.example.scame.retroflowmvp.BasePresenter
import com.example.scame.retroflowmvp.boards.BoardRawModel

interface BoardsPresenter<T>: BasePresenter<T> {

    interface BoardsView {

        fun onProgressChange(show: Boolean)

        fun onBoards(boards: List<BoardRawModel>)

        fun onAddBoard()

        fun onError(throwable: Throwable)
    }

    fun requestBoards()

    fun addBoardClick()
}