package com.example.scame.retroflowmvp.boards.view.sprints.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.scame.retroflowmvp.R
import com.example.scame.retroflowmvp.boards.addedit.models.ColumnEntity
import com.example.scame.retroflowmvp.boards.view.sprints.RetroSection

class RetroSectionsAdapter(private val retroSections: MutableList<ColumnEntity>,
                           private val context: Context,
                           private val onClick: (ColumnEntity) -> Unit): RecyclerView.Adapter<RetroSectionsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetroSectionsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.retro_section_item, parent, false)
        return RetroSectionsViewHolder(view, context, onClick)
    }

    override fun getItemCount(): Int = retroSections.size

    override fun onBindViewHolder(holder: RetroSectionsViewHolder, position: Int) {
        holder.bind(retroSections[position])
    }

    fun rebind(retroSections: List<ColumnEntity>) {
        this.retroSections.clear()
        this.retroSections.addAll(retroSections)
        notifyDataSetChanged()
    }
}