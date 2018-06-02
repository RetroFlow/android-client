package com.example.scame.retroflowmvp.boards

import com.example.scame.retroflowmvp.boards.addedit.models.BoardDefaultSettings
import io.reactivex.Completable
import io.reactivex.Single

interface BoardsRepository {

    fun createBoard(name: String, settings: BoardDefaultSettings): Single<BoardApiModel>

    fun editBoard(board: BoardRawModel)

    fun getBoards(): Single<List<BoardApiModel>>

    fun removeBoard(id: String): Completable

    fun getDefaultBoardSettings(): Single<BoardDefaultSettings>
}