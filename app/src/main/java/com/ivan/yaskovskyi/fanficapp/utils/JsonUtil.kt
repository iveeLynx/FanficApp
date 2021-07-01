package com.ivan.yaskovskyi.fanficapp.utils

import android.util.Log
import android.widget.Toast
import com.ivan.yaskovskyi.fanficapp.config.NetworkConfig
import com.ivan.yaskovskyi.fanficapp.model.Post
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.produce
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.lang.Exception

class JsonUtil {

    companion object {
        val instance = JsonUtil()
        val retrofit = NetworkConfig.instance.getApi()
    }


    fun checkUser(
            email: String,
            password: String
    ): Boolean {
        val userData = JSONObject()

        userData.put("email", email)
        userData.put("password", password)

        val requestBody = userData.toString().toRequestBody("application/json".toMediaTypeOrNull())
        var actionSuccess = false

        val job = GlobalScope.launch {
            try {
                val response = retrofit?.loginUser(requestBody)
                if (response != null) {
                    withContext(Dispatchers.IO) {
                        if (response.isSuccessful) {
                            var res = response.body()!!.string()
                            actionSuccess = res == "success"
                        } else {
                            Log.e("RETROFIT_ERROR", response.code().toString())
                        }
                    }
                }
            } catch (msg: Exception) {

            }
        }
        runBlocking {
            job.join()
        }
        return actionSuccess
    }

    fun getPosts(): List<Post> = runBlocking(Dispatchers.IO) {
        var posts: List<Post> = emptyList()
        try {
            val response = retrofit?.getPosts()
            if (response != null) {
                withContext(Dispatchers.IO) {
                    posts = if (response.isSuccessful) {
                        response.body()!!
                    } else {
                        emptyList()
                    }
                }
            }
        } catch (msg: Exception) {
            Log.e("Error connection", msg.toString())
        }
        return@runBlocking posts
    }

    fun sendNewUserData(
            userName: String,
            email: String,
            password: String
    ): Boolean {
        val userData = JSONObject()
        userData.put("userName", userName)
        userData.put("email", email)
        userData.put("password", password)

        val requestBody = userData.toString().toRequestBody("application/json".toMediaTypeOrNull())

        var actionSuccess = true

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofit?.registerUser(requestBody)
                if (response != null) {
                    withContext(Dispatchers.Main) {
                        actionSuccess = response.isSuccessful
                    }
                }
            } catch (msg: Exception) {
                Log.e("Error", msg.toString())
            }
        }
        return actionSuccess;
    }
}