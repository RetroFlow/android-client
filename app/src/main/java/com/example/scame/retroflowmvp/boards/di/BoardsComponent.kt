package com.example.scame.retroflowmvp.boards.di

import com.example.scame.retroflowmvp.boards.BoardsFragment
import com.example.scame.retroflowmvp.injection.PerModule
import dagger.Subcomponent

@PerModule
@Subcomponent(modules = [BoardsModule::class])
interface BoardsComponent {

    fun inject(boardsFragment: BoardsFragment)
}