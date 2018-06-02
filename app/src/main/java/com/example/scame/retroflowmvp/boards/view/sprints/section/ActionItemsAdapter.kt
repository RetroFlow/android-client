package com.example.scame.retroflowmvp.boards.view.sprints.section

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.scame.retroflowmvp.R
import com.example.scame.retroflowmvp.boards.addedit.models.ItemEntity
import com.example.scame.retroflowmvp.boards.view.sprints.ActionItem

class ActionItemsAdapter(private val actionItems: MutableList<ItemEntity>,
                         private val context: Context,
                         private val onClick: (ItemEntity) -> Unit): RecyclerView.Adapter<ActionItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.action_item_layout, parent, false)
        return ActionItemViewHolder(view, onClick)
    }

    override fun getItemCount(): Int = actionItems.size

    override fun onBindViewHolder(holder: ActionItemViewHolder, position: Int) {
        holder.bind(actionItems[position])
    }

    fun rebind(actionItems: List<ItemEntity>) {
        this.actionItems.clear()
        this.actionItems.addAll(actionItems)
        notifyDataSetChanged()
    }
}