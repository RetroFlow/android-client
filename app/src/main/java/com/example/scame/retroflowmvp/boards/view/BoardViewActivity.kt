package com.example.scame.retroflowmvp.boards.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.example.scame.retroflowmvp.R
import com.example.scame.retroflowmvp.boards.view.sprints.SprintsPagerAdapter
import com.example.scame.retroflowmvp.utils.setToolbarBackButton

class BoardViewActivity: AppCompatActivity() {

    companion object {

        private const val BOARD_ID_KEY = "board_kd_key"

        fun getIntent(boardId: String, context: Context): Intent {
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

    private lateinit var sprintsAdapter: SprintsPagerAdapter

    private val boardId by lazy {
        intent.getStringExtra(BOARD_ID_KEY)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_view)
        ButterKnife.bind(this)

        setupTabs()
        setupToolbar()
    }

    private fun setupToolbar() {
        toolbar.setToolbarBackButton(this)
    }

    private fun setupTabs() {
        sprintsAdapter = SprintsPagerAdapter(supportFragmentManager, boardId)
        sprintsPager.adapter = sprintsAdapter
        tabLayout.setupWithViewPager(sprintsPager)
    }
}