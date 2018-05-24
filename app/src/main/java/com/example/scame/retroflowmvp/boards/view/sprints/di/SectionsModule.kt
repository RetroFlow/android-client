package com.example.scame.retroflowmvp.boards.view.sprints.di

import com.example.scame.retroflowmvp.boards.view.sprints.presenter.RetroSectionsPresenter
import com.example.scame.retroflowmvp.boards.view.sprints.presenter.RetroSectionsPresenterImpl
import com.example.scame.retroflowmvp.boards.view.sprints.repository.RetroItemsRepository
import com.example.scame.retroflowmvp.injection.PerModule
import dagger.Module
import dagger.Provides

@Module
class SectionsModule {

    @Provides
    @PerModule
    fun provideRetroSectionsPresenter(retroItemsRepository: RetroItemsRepository): RetroSectionsPresenter<RetroSectionsPresenter.RetroSectionsView> =
            RetroSectionsPresenterImpl(retroItemsRepository)
}