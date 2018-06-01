package com.example.scame.retroflowmvp.injection

import com.example.scame.retroflowmvp.boards.di.BoardsComponent
import com.example.scame.retroflowmvp.boards.di.BoardsModule
import com.example.scame.retroflowmvp.boards.view.sprints.di.SectionsComponent
import com.example.scame.retroflowmvp.boards.view.sprints.di.SectionsModule
import com.example.scame.retroflowmvp.boards.view.sprints.section.action_item.di.ActionItemComponent
import com.example.scame.retroflowmvp.boards.view.sprints.section.action_item.di.ActionItemModule
import com.example.scame.retroflowmvp.entry_point.login.di.LoginComponent
import com.example.scame.retroflowmvp.entry_point.login.di.LoginModule
import com.example.scame.retroflowmvp.entry_point.registration.di.RegistrationComponent
import com.example.scame.retroflowmvp.entry_point.registration.di.RegistrationModule
import com.example.scame.retroflowmvp.profile.di.ProfileComponent
import com.example.scame.retroflowmvp.profile.di.ProfileModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun provideLoginComponent(loginModule: LoginModule): LoginComponent

    fun provideRegistrationComponent(registrationModule: RegistrationModule): RegistrationComponent

    fun provideActionItemComponent(actionItemModule: ActionItemModule): ActionItemComponent

    fun provideBoardsComponent(boardsModule: BoardsModule): BoardsComponent

    // remove
    fun provideRetroSectionsComponents(sectionsModule: SectionsModule): SectionsComponent

    fun provideProfileComponent(profileModule: ProfileModule): ProfileComponent

    // add SettingComponent
}