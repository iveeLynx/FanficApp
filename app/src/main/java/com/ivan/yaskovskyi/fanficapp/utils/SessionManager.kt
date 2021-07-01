package com.ivan.yaskovskyi.fanficapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.ivan.yaskovskyi.fanficapp.R

class SessionManager(context: Context) {

        private val sessionContext = context
    private val preferences: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = preferences.edit()

    fun checkUserLogin(): Boolean {
        return preferences.getBoolean("isLogged", false)
    }

    fun loginUser() {
        editor.putBoolean("isLogged", true).commit()
    }

    fun logoutUser() {
        editor.putBoolean("isLogged", false).commit()
    }

}