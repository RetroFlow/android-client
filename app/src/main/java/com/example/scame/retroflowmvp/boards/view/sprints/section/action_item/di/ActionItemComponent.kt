package com.example.scame.retroflowmvp.boards.view.sprints.section.action_item.di

import com.example.scame.retroflowmvp.boards.view.sprints.section.action_item.ActionItemActivity
import com.example.scame.retroflowmvp.injection.PerModule
import dagger.Subcomponent


@PerModule
@Subcomponent(modules = [ActionItemModule::class])
interface ActionItemComponent {

    fun inject(actionItemActivity: ActionItemActivity)
}