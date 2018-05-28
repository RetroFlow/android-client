package com.example.scame.retroflowmvp.boards

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.scame.retroflowmvp.R

class BoardsRvAdapter(private val boards: MutableList<BoardRawModel>,
                      private val context: Context,
                      private val onClick: (BoardRawModel) -> Unit) : RecyclerView.Adapter<BoardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder =
            BoardViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.board_item, parent, false),
                    onClick
            )

    override fun getItemCount(): Int = boards.size

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        holder.bind(boards[position])
    }

    fun rebind(boards: List<BoardRawModel>) {
        this.boards.clear()
        this.boards.addAll(boards)
        notifyDataSetChanged()
    }
}