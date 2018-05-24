package com.example.scame.retroflowmvp.boards.view.sprints.presenter

import com.example.scame.retroflowmvp.boards.view.sprints.repository.RetroItemsRepository
import io.reactivex.disposables.CompositeDisposable

class RetroSectionsPresenterImpl<T: RetroSectionsPresenter.RetroSectionsView>(
        private val retroItemsRepository: RetroItemsRepository
): RetroSectionsPresenter<T> {

    private var view: T? = null

    private val compositeDisposable = CompositeDisposable()

    override fun requestRetroSections(boardId: String) {
        compositeDisposable.add(
                retroItemsRepository
                        .getRetroSectionsForBoard(boardId)
                        .doOnSubscribe { view?.onProgressChanged(true) }
                        .doFinally { view?.onProgressChanged(false) }
                        .subscribe(
                                { view?.onRetroSections(it) },
                                { view?.onError(it) }
                        )
        )
    }

    override fun subscribe(view: T) {
        this.view = view
    }

    override fun unsubscribe() {
        this.view = null
        compositeDisposable.clear()
    }
}