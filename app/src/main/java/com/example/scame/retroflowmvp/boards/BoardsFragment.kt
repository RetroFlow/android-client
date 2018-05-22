package com.example.scame.retroflowmvp.boards

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.scame.retroflowmvp.R

class BoardsFragment: Fragment() {

    companion object {
        fun newInstance() = BoardsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.boards_fragment, container, false)
    }
}