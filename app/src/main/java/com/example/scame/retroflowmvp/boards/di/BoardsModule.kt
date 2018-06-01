package com.example.scame.retroflowmvp.boards.di

import android.content.SharedPreferences
import com.example.scame.retroflowmvp.boards.BoardsRepository
import com.example.scame.retroflowmvp.boards.addedit.presenter.BoardAddEditPresenter
import com.example.scame.retroflowmvp.boards.addedit.presenter.BoardAddEditPresenterImpl
import com.example.scame.retroflowmvp.boards.presenter.BoardsPresenter
import com.example.scame.retroflowmvp.boards.presenter.BoardsPresenterImpl
import com.example.scame.retroflowmvp.injection.ObserveOn
import com.example.scame.retroflowmvp.injection.PerModule
import com.example.scame.retroflowmvp.injection.SubscribeOn
import dagger.Module
import dagger.Provides

@Module
class BoardsModule {

    @Provides
    @PerModule
    fun provideBoardsPresenter(boardsRepository: BoardsRepository):
            BoardsPresenter<BoardsPresenter.BoardsView> =
            BoardsPresenterImpl(boardsRepository)

    @Provides
    @PerModule
    fun provideBoardAddEditPresenter(boardsRepository: BoardsRepository):
            BoardAddEditPresenter<BoardAddEditPresenter.BoardAddEditView> =
            BoardAddEditPresenterImpl(boardsRepository)
}