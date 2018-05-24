package com.example.scame.retroflowmvp.boards.view.sprints.di

import com.example.scame.retroflowmvp.boards.view.sprints.SprintBaseFragment
import com.example.scame.retroflowmvp.boards.view.sprints.curr_sprint.CurrentSprintFragment
import com.example.scame.retroflowmvp.boards.view.sprints.next_sprint.NextSprintFragment
import com.example.scame.retroflowmvp.injection.PerModule
import dagger.Subcomponent

@PerModule
@Subcomponent(modules = [SectionsModule::class])
interface SectionsComponent {

    fun inject(sprintBaseFragment: SprintBaseFragment)
}