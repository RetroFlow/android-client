package com.example.scame.retroflowmvp.injection

import com.example.scame.retroflowmvp.boards.di.BoardsComponent
import com.example.scame.retroflowmvp.boards.di.BoardsModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun provideBoardsComponent(boardsModule: BoardsModule): BoardsComponent
}