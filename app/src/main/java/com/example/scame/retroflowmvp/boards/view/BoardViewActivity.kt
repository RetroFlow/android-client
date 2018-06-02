package com.example.scame.retroflowmvp.boards.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import com.example.scame.retroflowmvp.R
import com.example.scame.retroflowmvp.RetroFlowApp
import com.example.scame.retroflowmvp.boards.BoardsRepository
import com.example.scame.retroflowmvp.boards.addedit.models.DeepBoard
import com.example.scame.retroflowmvp.boards.addedit.models.SprintEntity
import com.example.scame.retroflowmvp.boards.di.BoardsModule
import com.example.scame.retroflowmvp.boards.view.sprints.SprintsPagerAdapter
import com.example.scame.retroflowmvp.utils.setToolbarBackButton
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

// TODO: create related presenter
class BoardViewActivity : AppCompatActivity() {

    companion object {

        private const val BOARD_ID_KEY = "board_kd_key"

        fun getIntent(boardId: Int, context: Context): Intent {
            val intent = Intent(context, BoardViewActivity::class.java)
            intent.putExtra(BOARD_ID_KEY, boardId)

            return intent
        }
    }

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.sprints_pager)
    lateinit var sprintsPager: ViewPager

    @BindView(R.id.sprint_tabs)
    lateinit var tabLayout: TabLayout

    @BindView(R.id.progress_bar)
    lateinit var progressBar: ProgressBar

    @Inject
    lateinit var boardsRepository: BoardsRepository

    private var sprintsAdapter: SprintsPagerAdapter? = null

    private val boardComponent by lazy {
        RetroFlowApp.appComponent.provideBoardsComponent(BoardsModule())
    }

    private val boardId by lazy {
        intent.getIntExtra(BOARD_ID_KEY, -1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_view)
        ButterKnife.bind(this)

        setupToolbar()

        boardComponent.inject(this)
    }

    override fun onStart() {
        super.onStart()
        requestBoardWithSprintCreation()
    }

    private fun requestBoardWithSprintCreation() {
        if (sprintsAdapter == null) {
            getDeepBoard().flatMap { deepBoard ->
                if (deepBoard.currSprint == null || deepBoard.nextSprint == null) {
                    startNewSprints(deepBoard.id).toSingle { deepBoard }
                } else {
                    Single.just(deepBoard)
                }
            }.subscribe(
                    { setupTabs(it.currSprint, it.nextSprint) },
                    { Log.i("onxBoardView", it.toString()) }
            )
        }
    }

    private fun getDeepBoard(): Single<DeepBoard> {
        return boardsRepository
                .getDeepBoard(boardId)
                .doOnSubscribe { progressBar.visibility = View.VISIBLE }
                .doFinally { progressBar.visibility = View.GONE }
    }

    private fun startNewSprints(boardId: Int): Completable {
        return boardsRepository.startNewSprints(boardId)
    }

    private fun setupToolbar() {
        toolbar.setToolbarBackButton(this)
    }

    private fun setupTabs(currSprint: SprintEntity, nextSprint: SprintEntity) {
        sprintsAdapter = SprintsPagerAdapter(supportFragmentManager, currSprint, nextSprint)
        sprintsPager.adapter = sprintsAdapter
        tabLayout.setupWithViewPager(sprintsPager)
    }
}