package com.example.scame.retroflowmvp.boards.view.sprints.section

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import com.example.scame.retroflowmvp.ActionItemClickEvent
import com.example.scame.retroflowmvp.R
import com.example.scame.retroflowmvp.boards.view.sprints.RetroSection
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class ActionItemsActivity: AppCompatActivity() {

    companion object {
        private const val RETRO_SECTION_KEY = "retro_section_key"

        fun getIntent(context: Context, retroSection: RetroSection): Intent {
            val intent = Intent(context, ActionItemsActivity::class.java)
            intent.putExtra(RETRO_SECTION_KEY, retroSection)
            return intent
        }
    }

    @BindView(R.id.action_item_title)
    lateinit var actionsItemsRv: RecyclerView

    @BindView(R.id.progress_bar)
    lateinit var progresssBar: ProgressBar

    private val retroSection by lazy {
        intent.getParcelableExtra<RetroSection>(RETRO_SECTION_KEY)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.action_items_activity_layout)
        ButterKnife.bind(this)

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
        actionsItemsRv.layoutManager = LinearLayoutManager(this)
    }

    @Subscribe
    fun onActionItemClick(actionItemClickEvent: ActionItemClickEvent) {

    }
}