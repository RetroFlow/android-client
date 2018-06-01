package com.example.scame.retroflowmvp.boards.addedit.presenter

import com.example.scame.retroflowmvp.boards.BoardApiModel
import com.example.scame.retroflowmvp.boards.BoardsRepository
import io.reactivex.disposables.CompositeDisposable

class BoardAddEditPresenterImpl<T: BoardAddEditPresenter.BoardAddEditView>(
        private val boardsRepository: BoardsRepository
): BoardAddEditPresenter<T> {

    private var view: T? = null

    private val composite: CompositeDisposable = CompositeDisposable()

    override fun requestDefaultBoardSettings() {
        composite.add(boardsRepository
                .getDefaultBoardSettings()
                .doOnSubscribe { view?.onProgressChanged(true) }
                .doFinally { view?.onProgressChanged(false) }
                .subscribe(
                        { view?.onDefaultBoardSettings(it) },
                        { view?.onError(it) }
                )
        )
    }

    override fun createBoard(name: String, sprintStartDate: String, discussionPeriod: Int,
                             sprintDuration: Int, columnNames: List<String>) {

    }


    override fun subscribe(view: T) {
        this.view = view
    }

    override fun unsubscribe() {
        this.view = null
        this.composite.clear()
    }
}