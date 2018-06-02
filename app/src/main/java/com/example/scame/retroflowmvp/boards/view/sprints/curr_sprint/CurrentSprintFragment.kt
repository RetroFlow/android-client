package com.example.scame.retroflowmvp.boards.view.sprints.curr_sprint

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.scame.retroflowmvp.boards.addedit.models.SprintEntity
import com.example.scame.retroflowmvp.boards.view.sprints.SprintBaseFragment

class CurrentSprintFragment: SprintBaseFragment() {

    companion object {
        fun newInstance(currSprint: SprintEntity): Fragment {
            val args = Bundle()
            args.putParcelable(SPRINT_KEY, currSprint)

            val fragment = CurrentSprintFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override val isCurrentSprint: Boolean
        get() = true
}