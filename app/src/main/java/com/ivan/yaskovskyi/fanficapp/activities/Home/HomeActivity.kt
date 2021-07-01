package com.ivan.yaskovskyi.fanficapp.activities.Home

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.ivan.yaskovskyi.fanficapp.R
import com.ivan.yaskovskyi.fanficapp.activities.Login.LoginActivity
import com.ivan.yaskovskyi.fanficapp.activities.Posts.PostsActivity
import com.ivan.yaskovskyi.fanficapp.activities.Registration.RegistrationActivity
import com.ivan.yaskovskyi.fanficapp.utils.RoomService
import com.ivan.yaskovskyi.fanficapp.utils.SessionManager


class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val signIn: ImageButton = findViewById(R.id.sign_in)
        val signUp: ImageButton = findViewById(R.id.sign_up)
        val showPosts: ImageButton = findViewById(R.id.postsPage)

        initPreferences()

        signIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        signUp.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
        showPosts.setOnClickListener {
            val intent = Intent(this, PostsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initPreferences() {
        var session = SessionManager(applicationContext)
        session.logoutUser()
    }


}