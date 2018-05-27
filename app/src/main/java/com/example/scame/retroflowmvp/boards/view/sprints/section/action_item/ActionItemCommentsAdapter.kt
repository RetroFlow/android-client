package com.example.scame.retroflowmvp.boards.view.sprints.section.action_item

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.scame.retroflowmvp.R
import com.example.scame.retroflowmvp.boards.view.sprints.Comment

class ActionItemCommentsAdapter(private val comments: MutableList<Comment>,
                                private val context: Context): RecyclerView.Adapter<ActionItemCommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionItemCommentViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.action_item_comment_layout, parent, false)
        return ActionItemCommentViewHolder(view)
    }

    override fun getItemCount(): Int = comments.size

    override fun onBindViewHolder(holder: ActionItemCommentViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    fun rebind(comments: List<Comment>) {
        this.comments.clear()
        this.comments.addAll(comments)
        notifyDataSetChanged()
    }
}