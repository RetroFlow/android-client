package com.example.scame.retroflowmvp.boards.addedit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class BoardAddEditActivity: AppCompatActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, BoardAddEditActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}