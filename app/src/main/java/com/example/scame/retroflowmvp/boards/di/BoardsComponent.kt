package com.example.scame.retroflowmvp.boards.di

import com.example.scame.retroflowmvp.boards.BoardsFragment
import com.example.scame.retroflowmvp.boards.addedit.BoardAddEditActivity
import com.example.scame.retroflowmvp.boards.view.BoardViewActivity
import com.example.scame.retroflowmvp.injection.PerModule
import dagger.Subcomponent

@PerModule
@Subcomponent(modules = [BoardsModule::class])
interface BoardsComponent {

    fun inject(boardsFragment: BoardsFragment)

    fun inject(boardAddEditActivity: BoardAddEditActivity)

    fun inject(boardViewActivity: BoardViewActivity)
}