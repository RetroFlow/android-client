package com.example.scame.retroflowmvp.boards.addedit.presenter

import com.example.scame.retroflowmvp.boards.BoardApiModel
import com.example.scame.retroflowmvp.boards.BoardsRepository
import com.example.scame.retroflowmvp.boards.addedit.models.BoardDefaultSettings
import com.example.scame.retroflowmvp.boards.addedit.models.ColumnName
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class BoardAddEditPresenterImpl<T : BoardAddEditPresenter.BoardAddEditView>(
        private val boardsRepository: BoardsRepository
) : BoardAddEditPresenter<T> {

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

    override fun createBoard(name: String, sprintStartDate: Date, discussionPeriod: Int,
                             sprintDuration: Int, columnNames: List<String>) {
        composite.add(boardsRepository.createBoard(
                name, BoardDefaultSettings(sprintStartDate, discussionPeriod, "", sprintDuration,
                columnNames.map { ColumnName(it) }))
                .doOnSubscribe { view?.onProgressChanged(true) }
                .doFinally { view?.onProgressChanged(false) }
                .subscribe(
                        { view?.onBoardCreated() },
                        { view?.onError(it) }
                )
        )
    }


    override fun subscribe(view: T) {
        this.view = view
    }

    override fun unsubscribe() {
        this.view = null
        this.composite.clear()
    }
}