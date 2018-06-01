package com.example.scame.retroflowmvp.boards

import com.example.scame.retroflowmvp.boards.addedit.models.BoardDefaultSettings
import io.reactivex.Completable
import io.reactivex.Single

interface BoardsRepository {

    fun createBoard(board: BoardRawModel)

    fun editBoard(board: BoardRawModel)

    fun getBoards(): Single<List<BoardRawModel>>

    fun removeBoard(id: String): Completable

    fun getDefaultBoardSettings(): Single<BoardDefaultSettings>
}