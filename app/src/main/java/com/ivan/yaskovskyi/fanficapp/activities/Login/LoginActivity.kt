package com.ivan.yaskovskyi.fanficapp.activities.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ivan.yaskovskyi.fanficapp.R
import com.ivan.yaskovskyi.fanficapp.activities.Posts.PostsActivity
import com.ivan.yaskovskyi.fanficapp.utils.JsonUtil

class LoginActivity : AppCompatActivity() {

    lateinit var email:EditText
    lateinit var password:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.emailLogin)
        password = findViewById(R.id.passwordLogin)
        val loginUser: Button = findViewById(R.id.getLogin)
        loginUser.setOnClickListener{
            if(login(email.text.toString(), password.text.toString())) {
                val intent = Intent(this, PostsActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Error to login. Please check email/password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun login(email: String, password: String) : Boolean{
       return JsonUtil.instance.checkUser(email, password)
    }

}