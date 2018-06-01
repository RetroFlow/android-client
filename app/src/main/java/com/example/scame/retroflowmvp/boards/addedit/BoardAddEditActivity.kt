package com.example.scame.retroflowmvp.boards.addedit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.scame.retroflowmvp.R
import com.example.scame.retroflowmvp.RetroFlowApp
import com.example.scame.retroflowmvp.boards.addedit.models.BoardDefaultSettings
import com.example.scame.retroflowmvp.boards.addedit.presenter.BoardAddEditPresenter
import com.example.scame.retroflowmvp.boards.di.BoardsModule
import com.example.scame.retroflowmvp.utils.setToolbarBackButton
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class BoardAddEditActivity: AppCompatActivity(), BoardAddEditPresenter.BoardAddEditView {

    companion object {
        fun getIntent(context: Context) = Intent(context, BoardAddEditActivity::class.java)
    }

    @BindView(R.id.board_name_et)
    lateinit var boardNameEt: TextView

    @BindView(R.id.sprint_start_date_layout)
    lateinit var sprintStartDateLayout: View

    @BindView(R.id.sprint_start_date_tv)
    lateinit var sprintStartDateTv: TextView

    @BindView(R.id.columns_et)
    lateinit var columnsEt: EditText

    @BindView(R.id.discussion_period_et)
    lateinit var discussionPeriodEt: EditText

    @BindView(R.id.sprint_duration_et)
    lateinit var sprintDurationEt: EditText

    @BindView(R.id.progress_bar)
    lateinit var progressBar: ProgressBar

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    private lateinit var sprintStartDate: Date

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
        toolbar.setToolbarBackButton(this)
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
        val sdf = SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.US)
        sprintStartDate = boardDefaultSettings.sprintStartDate
        sprintStartDateTv.text = sdf.format(boardDefaultSettings.sprintStartDate)
        discussionPeriodEt.setText(boardDefaultSettings.discussionPeriod.toString())
        sprintDurationEt.setText(boardDefaultSettings.sprintDuration.toString())
        columnsEt.setText(boardDefaultSettings.columnNames.joinToString { it.name })
    }

    override fun onProgressChanged(show: Boolean) {
        progressBar.visibility =  if (show) View.VISIBLE else View.GONE
    }

    override fun onError(throwable: Throwable) {
    }

    @OnClick(R.id.board_create_btn)
    fun onBoardAddClick() {
        boardAddEditPresenter.createBoard(
                boardNameEt.text.toString(),
                sprintStartDate,
                discussionPeriodEt.text.toString().toInt(),
                sprintDurationEt.text.toString().toInt(),
                columnsEt.text.toString().split(", "))
    }
}