package com.example.scame.retroflowmvp.boards

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.example.scame.retroflowmvp.R

class BoardsFragment: Fragment() {

    @BindView(R.id.boards_rv)
    lateinit var boardsRv: RecyclerView

    companion object {
        fun newInstance() = BoardsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.boards_fragment, container, false)
        ButterKnife.bind(this, view)

        return view
    }
}