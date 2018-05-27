package com.example.scame.retroflowmvp.utils

import android.app.Activity
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.scame.retroflowmvp.R

fun Activity.hideKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = this.currentFocus
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Toolbar.setToolbarBackButton(activity: AppCompatActivity) {
    activity.setSupportActionBar(this)
    this.setNavigationIcon(R.drawable.abc_ic_ab_back_material)
    this.setNavigationOnClickListener { activity.finish() }
}

fun FragmentManager.replaceFragment(@IdRes containerId: Int, fragment: Fragment, tag: String?, addToBackStack: Boolean = false) {
    val transaction = this.beginTransaction()
    transaction.replace(containerId, fragment, tag)
    if (addToBackStack) transaction.addToBackStack(null)
    transaction.commit()
}