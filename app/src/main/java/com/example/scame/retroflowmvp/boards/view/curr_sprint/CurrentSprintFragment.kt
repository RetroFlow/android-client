package com.example.scame.retroflowmvp.boards.view.curr_sprint

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.example.scame.retroflowmvp.R

class CurrentSprintFragment: Fragment() {

    companion object {
        fun newInstance() = CurrentSprintFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.current_sprint_fragment_layout, container, false)
        ButterKnife.bind(this, view)

        return view
    }
}