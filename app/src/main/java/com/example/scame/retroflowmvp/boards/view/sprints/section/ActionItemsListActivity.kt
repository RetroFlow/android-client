package com.example.scame.retroflowmvp.boards.view.sprints.section

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.example.scame.retroflowmvp.ActionItemClickEvent
import com.example.scame.retroflowmvp.R
import com.example.scame.retroflowmvp.boards.view.sprints.RetroSection
import com.example.scame.retroflowmvp.boards.view.sprints.section.action_item.ActionItemActivity
import com.example.scame.retroflowmvp.setToolbarBackButton
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class ActionItemsListActivity: AppCompatActivity() {

    companion object {
        private const val RETRO_SECTION_KEY = "retro_section_key"

        fun getIntent(context: Context, retroSection: RetroSection): Intent {
            val intent = Intent(context, ActionItemsListActivity::class.java)
            intent.putExtra(RETRO_SECTION_KEY, retroSection)
            return intent
        }
    }

    @BindView(R.id.action_items_rv)
    lateinit var actionsItemsRv: RecyclerView

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    private val retroSection by lazy {
        intent.getParcelableExtra<RetroSection>(RETRO_SECTION_KEY)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.action_items_list_activity_layout)
        ButterKnife.bind(this)

        setupToolbar()
        setupActionItemsList()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    private fun setupActionItemsList() {
        actionsItemsRv.adapter = ActionItemsAdapter(retroSection.actionItems.toMutableList(), this)
        actionsItemsRv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        actionsItemsRv.layoutManager = LinearLayoutManager(this)
    }

    @Subscribe
    fun onActionItemClick(actionItemClickEvent: ActionItemClickEvent) {
        startActivity(ActionItemActivity.getIntent(this, actionItemClickEvent.actionItem))
    }

    private fun setupToolbar() {
        toolbar.setToolbarBackButton(this)
    }
}