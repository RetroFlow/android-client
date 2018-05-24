package com.example.scame.retroflowmvp.boards.view.sprints.section

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.scame.retroflowmvp.R
import com.example.scame.retroflowmvp.boards.view.sprints.ActionItem

class ActionItemsAdapter(private val actionItems: MutableList<ActionItem>,
                         private val context: Context): RecyclerView.Adapter<ActionItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.action_item_layout, parent, false)
        return ActionItemViewHolder(view)
    }

    override fun getItemCount(): Int = actionItems.size

    override fun onBindViewHolder(holder: ActionItemViewHolder, position: Int) {
        holder.bind(actionItems[position])
    }

    fun rebind(actionItems: List<ActionItem>) {
        this.actionItems.clear()
        this.actionItems.addAll(actionItems)
        notifyDataSetChanged()
    }
}