package com.example.scame.retroflowmvp.boards.view.sprints.section.action_item

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.scame.retroflowmvp.R
import com.example.scame.retroflowmvp.boards.view.sprints.Comment

class ActionItemCommentViewHolder(view: View): RecyclerView.ViewHolder(view) {

    init {
        ButterKnife.bind(this, view)
    }

    @BindView(R.id.author_tv)
    lateinit var authorTv: TextView
    @BindView(R.id.comment_text)
    lateinit var commentTV: TextView

    fun bind(comment: Comment) {
        authorTv.text = comment.author
        commentTV.text = comment.text
    }
}