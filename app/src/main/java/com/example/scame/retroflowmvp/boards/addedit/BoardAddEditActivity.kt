package com.example.scame.retroflowmvp.boards.addedit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import butterknife.ButterKnife
import com.example.scame.retroflowmvp.R
import com.example.scame.retroflowmvp.RetroFlowApp
import com.example.scame.retroflowmvp.boards.addedit.models.BoardDefaultSettings
import com.example.scame.retroflowmvp.boards.addedit.presenter.BoardAddEditPresenter
import com.example.scame.retroflowmvp.boards.di.BoardsModule
import javax.inject.Inject

class BoardAddEditActivity: AppCompatActivity(), BoardAddEditPresenter.BoardAddEditView {

    companion object {
        fun getIntent(context: Context) = Intent(context, BoardAddEditActivity::class.java)
    }

    private val boardsComponent by lazy {
        RetroFlowApp.appComponent.provideBoardsComponent(BoardsModule())
    }

    @Inject
    lateinit var boardAddEditPresenter: BoardAddEditPresenter<BoardAddEditPresenter.BoardAddEditView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_add)
        ButterKnife.bind(this)

        setupInjection()
    }

    override fun onStart() {
        super.onStart()
        boardAddEditPresenter.subscribe(this)
        boardAddEditPresenter.requestDefaultBoardSettings()
    }

    override fun onStop() {
        super.onStop()
        boardAddEditPresenter.unsubscribe()
    }

    private fun setupInjection() {
        boardsComponent.inject(this)
    }

    override fun onBoardCreated() {
        finish()
    }

    override fun onDefaultBoardSettings(boardDefaultSettings: BoardDefaultSettings) {
    }

    override fun onProgressChanged(show: Boolean) {
    }

    override fun onError(throwable: Throwable) {
    }
}