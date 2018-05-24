package com.example.scame.retroflowmvp.boards.view.sprints.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.scame.retroflowmvp.R
import com.example.scame.retroflowmvp.boards.view.sprints.RetroSection

class RetroSectionsAdapter(private val retroSections: MutableList<RetroSection>): RecyclerView.Adapter<RetroSectionsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetroSectionsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.board_item, parent, false)
        return RetroSectionsViewHolder(view)
    }

    override fun getItemCount(): Int = retroSections.size

    override fun onBindViewHolder(holder: RetroSectionsViewHolder, position: Int) {
        holder.bind(retroSections[position])
    }

    fun rebind(retroSections: List<RetroSection>) {
        this.retroSections.clear()
        this.retroSections.addAll(retroSections)
        notifyDataSetChanged()
    }
}