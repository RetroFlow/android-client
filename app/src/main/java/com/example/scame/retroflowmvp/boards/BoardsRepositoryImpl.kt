package com.example.scame.retroflowmvp.boards

import io.reactivex.Completable
import io.reactivex.Single

class BoardsRepositoryImpl: BoardsRepository {

    override fun createBoard(board: BoardRawModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun editBoard(board: BoardRawModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBoards(): Single<List<BoardRawModel>> {
        val board1 = BoardRawModel("1", "Board 1", "In Progress")
        val board2 = BoardRawModel("2", "Board 2", "Open")
        val board3 = BoardRawModel("3", "Board 3", "Voting")

        return Single.just(listOf(board1, board2, board3))
    }

    override fun removeBoard(id: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}