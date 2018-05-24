package com.example.scame.retroflowmvp.boards.view.sprints.next_sprint

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.scame.retroflowmvp.boards.view.sprints.SprintBaseFragment
import com.example.scame.retroflowmvp.boards.view.sprints.curr_sprint.CurrentSprintFragment

class NextSprintFragment: SprintBaseFragment() {

    override val isCurrentSprint: Boolean
        get() = false

    companion object {
        fun newInstance(boardId: String): Fragment {
            val args = Bundle()
            args.putString(BOARD_ID_KEY, boardId)

            val fragment = NextSprintFragment()
            fragment.arguments = args

            return fragment
        }
    }
}