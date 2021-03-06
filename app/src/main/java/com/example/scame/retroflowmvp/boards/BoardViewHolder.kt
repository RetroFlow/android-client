package com.example.scame.retroflowmvp.boards

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.scame.retroflowmvp.R

class BoardViewHolder(view: View, private val onClick: (BoardApiModel) -> Unit): RecyclerView.ViewHolder(view) {

    @BindView(R.id.board_card)
    lateinit var boardCard: CardView
    @BindView(R.id.board_name_tv)
    lateinit var boardName: TextView
    @BindView(R.id.board_stage_tv)
    lateinit var boardStage: TextView
    @BindView(R.id.admin_logo_iv)
    lateinit var adminLogo: ImageView

    init {
        ButterKnife.bind(this, view)
    }

    fun bind(board: BoardApiModel) {
        boardName.text = board.name
        boardStage.text = board.status
        boardCard.setOnClickListener { onClick(board) }
    }
}