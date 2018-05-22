package com.example.scame.retroflowmvp.boards.di

import com.example.scame.retroflowmvp.boards.BoardsRepository
import com.example.scame.retroflowmvp.boards.presenter.BoardsPresenter
import com.example.scame.retroflowmvp.boards.presenter.BoardsPresenterImpl
import com.example.scame.retroflowmvp.injection.PerModule
import dagger.Module
import dagger.Provides

@Module
class BoardsModule {

    @Provides
    @PerModule
    fun provideBoardsPresenter(boardsRepository: BoardsRepository): BoardsPresenter<BoardsPresenter.BoardsView> =
            BoardsPresenterImpl(boardsRepository)
}