package com.example.scame.retroflowmvp.boards

import com.example.scame.retroflowmvp.boards.addedit.models.BoardDefaultSettings
import com.example.scame.retroflowmvp.boards.addedit.models.DeepBoard
import io.reactivex.Completable
import io.reactivex.Single

interface BoardsRepository {

    fun createBoard(name: String, settings: BoardDefaultSettings): Single<BoardApiModel>

    fun editBoard(board: BoardRawModel)

    fun getBoards(): Single<List<BoardApiModel>>

    fun getDeepBoard(id: Int): Single<DeepBoard>

    fun removeBoard(id: String): Completable

    fun getDefaultBoardSettings(): Single<BoardDefaultSettings>

    fun startNewSprints(id: Int): Completable
}