package com.ivan.yaskovskyi.fanficapp.config

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class NetworkConfig {

    private val BASE_URL = "https://polar-earth-68195.herokuapp.com/"

    companion object {
        val instance = NetworkConfig()
    }


    fun getApi(): SpringApi? {
        lateinit var mRetrofit: Retrofit
        val client = OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build()
        try {
            mRetrofit = Retrofit.Builder().client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        } catch (msg: Exception) {
            Log.e("RETROFIT", msg.toString())
        }

        return mRetrofit.create<SpringApi>(SpringApi::class.java)
    }
}