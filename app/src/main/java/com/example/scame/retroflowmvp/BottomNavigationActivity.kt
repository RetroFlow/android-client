package com.example.scame.retroflowmvp

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import butterknife.BindView
import butterknife.ButterKnife
import com.example.scame.retroflowmvp.boards.BoardsFragment
import com.example.scame.retroflowmvp.boards.di.BoardsModule
import com.example.scame.retroflowmvp.profile.ProfileFragment
import com.example.scame.retroflowmvp.profile.di.ProfileModule
import com.example.scame.retroflowmvp.settings.SettingsFragment
import com.example.scame.retroflowmvp.utils.replaceFragment

class BottomNavigationActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, BottomNavigationActivity::class.java)
    }

    @BindView(R.id.bottom_navigation)
    lateinit var bottomNavigation: BottomNavigationView

    val boardsComponent by lazy {
        RetroFlowApp.appComponent.provideBoardsComponent(BoardsModule())
    }

    val profileComponent by lazy {
        RetroFlowApp.appComponent.provideProfileComponent(ProfileModule())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)
        ButterKnife.bind(this)

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.board_menu -> { replaceFragment(BoardsFragment.newInstance(), BoardsFragment.javaClass.canonicalName) }
                R.id.settings_menu -> { replaceFragment(SettingsFragment.newInstance(), SettingsFragment.javaClass.canonicalName) }
                R.id.profile_menu -> { replaceFragment(ProfileFragment.newInstance(), ProfileFragment.javaClass.canonicalName) }
            }
            true
        }

        bottomNavigation.selectedItemId = R.id.board_menu
    }

    private fun replaceFragment(fragment: Fragment, tag: String?, addToBackStack: Boolean = false) {
        supportFragmentManager.replaceFragment(R.id.container_fl, fragment, tag, addToBackStack)
    }
}
