package com.example.scame.retroflowmvp.boards.addedit

import android.app.DatePickerDialog
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

class BoardAddEditActivity: AppCompatActivity(), BoardAddEditPresenter.BoardAddEditView, com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener {

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

        sprintStartDateLayout.setOnClickListener {
            pickDate()
        }
    }

    private fun pickDate() {
        val now = Calendar.getInstance()
        val dpd = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        )

        dpd.show(fragmentManager, "datepicker")
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

    // TODO: move date manipulation to presenter, eliminate duplication
    override fun onDefaultBoardSettings(boardDefaultSettings: BoardDefaultSettings) {
        val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.US)
        sprintStartDate = boardDefaultSettings.sprintStartDate
        sprintStartDateTv.text = sdf.format(boardDefaultSettings.sprintStartDate)
        discussionPeriodEt.setText(boardDefaultSettings.discussionPeriod.toString())
        sprintDurationEt.setText(boardDefaultSettings.sprintDuration.toString())
        columnsEt.setText(boardDefaultSettings.columnNames.joinToString { it.name })
    }

    // TODO: move date manipulation to presenter, eliminate duplication
    override fun onDateSet(view: com.wdullaer.materialdatetimepicker.date.DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, monthOfYear, dayOfMonth)
        sprintStartDate = Date(calendar.timeInMillis)

        val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.US)
        sprintStartDateTv.text = sdf.format(sprintStartDate)
    }

    override fun onProgressChanged(show: Boolean) {
        progressBar.visibility =  if (show) View.VISIBLE else View.GONE
    }

    override fun onError(throwable: Throwable) {
    }

    @OnClick(R.id.board_create_btn)
    fun onBoardAddClick() {
        if (!validate()) {
            return
        }

        boardAddEditPresenter.createBoard(
                boardNameEt.text.toString(),
                sprintStartDate,
                discussionPeriodEt.text.toString().toInt(),
                sprintDurationEt.text.toString().toInt(),
                columnsEt.text.toString().split(", "))
    }

    // TODO: move validation login to presenter
    private fun validate(): Boolean {
        var valid = true

        if (boardNameEt.text.isBlank()) {
            boardNameEt.error = "Cannot be blank"
            valid = false
        }

        if (discussionPeriodEt.text.isBlank() || discussionPeriodEt.text.toString().toInt() <= 0) {
            discussionPeriodEt.error = "Cannot be less than one or empty"
            valid = false
        }

        if (sprintDurationEt.text.isBlank() || sprintDurationEt.text.toString().toInt() <= 0) {
            sprintDurationEt.error = "Cannot be less than one or empty"
            valid = false
        }

        if (columnsEt.text.isBlank()) {
            columnsEt.error = "Cannot be blank"
            valid = false
        }

        return valid
    }
}