package com.mani.socialapp.util

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(val context: Context) {
    private val prefName = "SocialMediaApp"
    private val sharedPref: SharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    fun save(KEY_NAME: String, value: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(KEY_NAME, value)
        editor.apply()
    }

    fun getValueInt(KEY_NAME: String): Int = sharedPref.getInt(KEY_NAME, 1)
}
