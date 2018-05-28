package com.example.scame.retroflowmvp.boards.view.sprints.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.scame.retroflowmvp.R
import com.example.scame.retroflowmvp.SectionClickEvent
import com.example.scame.retroflowmvp.boards.view.sprints.RetroSection

class RetroSectionsViewHolder(view: View,
                              private val context: Context,
                              private val onClick: (RetroSection) -> Unit): RecyclerView.ViewHolder(view) {

    @BindView(R.id.section_id_tv)
    lateinit var sectionId: TextView
    @BindView(R.id.section_name)
    lateinit var sectionName: TextView
    @BindView(R.id.section_root_view)
    lateinit var sectionRoot: View

    init {
        ButterKnife.bind(this, view)
    }

    fun bind(retroSection: RetroSection) {
        sectionId.text = retroSection.sectionId
        sectionName.text = retroSection.name
        sectionRoot.setBackgroundColor(if (retroSection.open) {
            context.resources.getColor(R.color.light_blue)
        } else {
            context.resources.getColor(R.color.dark_blue)
        })

        sectionRoot.setOnClickListener { onClick(retroSection) }
    }
}