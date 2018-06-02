package com.example.scame.retroflowmvp.boards.view.sprints.next_sprint

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.scame.retroflowmvp.boards.addedit.models.SprintEntity
import com.example.scame.retroflowmvp.boards.view.sprints.SprintBaseFragment

class NextSprintFragment: SprintBaseFragment() {

    override val isCurrentSprint: Boolean
        get() = false

    companion object {
        fun newInstance(nextSprint: SprintEntity): Fragment {
            val args = Bundle()
            args.putParcelable(SPRINT_KEY, nextSprint)

            val fragment = NextSprintFragment()
            fragment.arguments = args

            return fragment
        }
    }
}