package com.example.scame.retroflowmvp.boards.view.sprints.section.action_item.di

import com.example.scame.retroflowmvp.boards.view.sprints.section.action_item.presenter.ActionItemPresenter
import com.example.scame.retroflowmvp.boards.view.sprints.section.action_item.presenter.ActionItemPresenterImpl
import com.example.scame.retroflowmvp.boards.view.sprints.section.action_item.repository.ActionItemRepository
import com.example.scame.retroflowmvp.injection.PerModule
import dagger.Module
import dagger.Provides

@Module
class ActionItemModule {

    @Provides
    @PerModule
    fun provideActionItemPresenter(actionItemRepository: ActionItemRepository):
            ActionItemPresenter<ActionItemPresenter.ActionItemView> =
                ActionItemPresenterImpl(actionItemRepository)
}