package com.example.scame.retroflowmvp.boards.view.sprints.curr_sprint

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.scame.retroflowmvp.boards.view.sprints.SprintBaseFragment

class CurrentSprintFragment: SprintBaseFragment() {

    companion object {
        fun newInstance(boardId: String): Fragment {
            val args = Bundle()
            args.putString(BOARD_ID_KEY, boardId)

            val fragment = CurrentSprintFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override val isCurrentSprint: Boolean
        get() = true
}