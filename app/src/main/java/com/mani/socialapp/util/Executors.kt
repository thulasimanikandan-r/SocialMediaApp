package com.mani.socialapp.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

fun AppCompatActivity.addFragment(frameId: Int, fragment: Fragment, fragmentTag: String?) {
    supportFragmentManager.inTransaction { add(frameId, fragment, fragmentTag) }
}


fun AppCompatActivity.replaceFragment(frameId: Int, fragment: Fragment, fragmentTag: String?) {
    supportFragmentManager.inTransaction { replace(frameId, fragment, fragmentTag) }
}

fun AppCompatActivity.updateActionBarTitle(actionBarTitle: Int, isEnabled: Boolean) {
    supportActionBar?.apply {
        setDisplayHomeAsUpEnabled(isEnabled)
        title = getString(actionBarTitle)
    }
}