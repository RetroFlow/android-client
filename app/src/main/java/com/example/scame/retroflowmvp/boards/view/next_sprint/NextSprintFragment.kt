package com.example.scame.retroflowmvp.boards.view.next_sprint

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.example.scame.retroflowmvp.R

class NextSprintFragment: Fragment() {

    companion object {
        fun newInstance() = NextSprintFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.next_sprint_fragment_layout, container, false)
        ButterKnife.bind(this, view)

        return view
    }
}