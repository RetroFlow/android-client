package com.example.scame.retroflowmvp.boards.view.sprints.section

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.scame.retroflowmvp.R
import com.example.scame.retroflowmvp.boards.view.sprints.ActionItem

class ActionItemViewHolder(view: View, private val onClick: (ActionItem) -> Unit): RecyclerView.ViewHolder(view) {

    @BindView(R.id.action_item_root)
    lateinit var actionItemRoot: View

    @BindView(R.id.action_item_title)
    lateinit var actionItemTitle: TextView

    init {
        ButterKnife.bind(this, view)
    }

    fun bind(actionItem: ActionItem) {
        actionItemTitle.text = actionItem.title
        actionItemRoot.setOnClickListener { onClick(actionItem) }
    }
}