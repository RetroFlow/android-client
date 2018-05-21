package com.example.scame.retroflowmvp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import butterknife.ButterKnife

class BottomNavigationActivity : AppCompatActivity() {

    lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)
        ButterKnife.bind(this)

        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.board_menu -> { }
                R.id.settings_menu -> { }
                R.id.profile_menu -> { }
            }
            true
        }
    }
}
