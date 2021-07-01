package com.ivan.yaskovskyi.fanficapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ivan.yaskovskyi.fanficapp.activities.Home.HomeActivity
import com.ivan.yaskovskyi.fanficapp.utils.RoomService
import com.ivan.yaskovskyi.fanficapp.utils.SessionManager


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRoom()
        initPreferences()

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun initRoom() {
        RoomService.setContext(applicationContext)
        RoomService().getFavourites()
    }

    private fun initPreferences() {
        var session = SessionManager(applicationContext)
        session.logoutUser()
    }
}