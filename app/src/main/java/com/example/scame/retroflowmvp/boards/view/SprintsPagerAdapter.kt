package com.example.scame.retroflowmvp.boards.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.scame.retroflowmvp.boards.view.curr_sprint.CurrentSprintFragment
import com.example.scame.retroflowmvp.boards.view.next_sprint.NextSprintFragment

class SprintsPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    companion object {
        private const val PAGES_COUNT = 2

        private const val CURRENT_SPRINT_POS = 0
        private const val NEXT_SPRINT_POS = 1
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            CURRENT_SPRINT_POS -> CurrentSprintFragment.newInstance()
            NEXT_SPRINT_POS -> NextSprintFragment.newInstance()
            else -> throw IllegalStateException("no such tab")
        }
    }

    override fun getCount(): Int = PAGES_COUNT

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            CURRENT_SPRINT_POS -> "Current"
            NEXT_SPRINT_POS -> "Next"
            else -> throw IllegalStateException("no such title")
        }
    }
}