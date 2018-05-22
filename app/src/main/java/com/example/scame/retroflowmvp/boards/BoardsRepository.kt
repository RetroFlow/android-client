package com.example.scame.retroflowmvp.boards

import io.reactivex.Completable
import io.reactivex.Single

interface BoardsRepository {

    fun createBoard(board: BoardRawModel)

    fun editBoard(board: BoardRawModel)

    fun getBoards(): Single<List<BoardRawModel>>

    fun removeBoard(id: String): Completable
}