package com.ivan.yaskovskyi.fanficapp.activities.Registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ivan.yaskovskyi.fanficapp.R
import com.ivan.yaskovskyi.fanficapp.activities.Home.HomeActivity
import com.ivan.yaskovskyi.fanficapp.utils.JsonUtil

class RegistrationActivity : AppCompatActivity() {

    lateinit var password: EditText
    lateinit var username: EditText
    lateinit var email: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        username = findViewById(R.id.userName)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)

        val registerUser: Button = findViewById(R.id.postRegister)

        registerUser.setOnClickListener {
            registerUser(username.text.toString(), email.text.toString(), password.text.toString())
        }
    }

    private fun registerUser(userName: String, email: String, password: String) {
        var message: Toast = Toast.makeText(applicationContext, "Application error", Toast.LENGTH_SHORT)

        if (checkFields(userName, email, password)) {
            val result: Boolean = JsonUtil.instance.sendNewUserData(userName, email, password)
            message = if (result) {
                Toast.makeText(applicationContext, "Registration successful", Toast.LENGTH_SHORT)
            } else {
                Toast.makeText(applicationContext, "Server error", Toast.LENGTH_SHORT)
            }
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        } else {
            message = Toast.makeText(applicationContext, "Marked data is required", Toast.LENGTH_SHORT)
        }
        message.show()
    }

    private fun checkFields(userName: String, email: String, password: String): Boolean {
        var status = true
        if (isEmpty(userName)) {
            this.username.error = "Username is required"
            status = false
        }
        if (isEmpty(password)) {
            this.password.error = "Password is required"
            status = false
        }
        if (!isEmailValid(email)) {
            this.email.error = "Email is not valid. Example: fanapp@site.com"
            status = false
        }
        return status
    }

    private fun isEmailValid(email: String): Boolean {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches())
    }

    private fun isEmpty(field: String): Boolean {
        return TextUtils.isEmpty(field)
    }

}