package com.example.scame.retroflowmvp.boards.view.sprints.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.ButterKnife
import com.example.scame.retroflowmvp.boards.view.sprints.RetroSection

class RetroSectionsViewHolder(view: View): RecyclerView.ViewHolder(view) {

    init {
        ButterKnife.bind(this, view)
    }

    fun bind(retroSection: RetroSection) {

    }
}