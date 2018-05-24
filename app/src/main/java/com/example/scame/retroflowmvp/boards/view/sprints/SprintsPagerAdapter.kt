package com.example.scame.retroflowmvp.boards.view.sprints

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.scame.retroflowmvp.boards.view.sprints.curr_sprint.CurrentSprintFragment
import com.example.scame.retroflowmvp.boards.view.sprints.next_sprint.NextSprintFragment

class SprintsPagerAdapter(fragmentManager: FragmentManager,
                          private val boardId: String): FragmentPagerAdapter(fragmentManager) {

    companion object {
        private const val PAGES_COUNT = 2

        private const val CURRENT_SPRINT_POS = 0
        private const val NEXT_SPRINT_POS = 1
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            CURRENT_SPRINT_POS -> CurrentSprintFragment.newInstance(boardId)
            NEXT_SPRINT_POS -> NextSprintFragment.newInstance(boardId)
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