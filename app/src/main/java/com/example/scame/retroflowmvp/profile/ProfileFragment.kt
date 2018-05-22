package com.example.scame.retroflowmvp.profile

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.scame.retroflowmvp.R

class ProfileFragment: Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }
}