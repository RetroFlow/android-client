package com.example.scame.retroflowmvp.boards.presenter

import com.example.scame.retroflowmvp.boards.BoardsRepository
import io.reactivex.disposables.CompositeDisposable

class BoardsPresenterImpl<T : BoardsPresenter.BoardsView>(
        private val boardsRepository: BoardsRepository
) : BoardsPresenter<T> {

    private var view: T? = null

    private val cd = CompositeDisposable()

    override fun requestBoards() {
        cd.add(
                boardsRepository.getBoards()
                        .doOnSubscribe { view?.onProgressChange(true) }
                        .doFinally { view?.onProgressChange(false) }
                        .subscribe(
                                { view?.onBoards(it) },
                                { view?.onError(it) }
                        )
        )
    }

    override fun addBoardClick() {
        view?.onAddBoard()
    }

    override fun subscribe(view: T) {
        this.view = view
    }

    override fun unsubscribe() {
        this.view = null
        cd.clear()
    }
}