package com.ivan.yaskovskyi.fanficapp.config

import com.ivan.yaskovskyi.fanficapp.model.Post
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*


interface SpringApi {

    @POST("/registration/process_register")
    suspend fun registerUser(@Body requestBody: RequestBody): Response<ResponseBody>

    @POST("/login/process_login")
    suspend fun loginUser(@Body requestBody: RequestBody): Response<ResponseBody>


    @GET("/posts/show")
    suspend fun getPosts(): Response<List<Post>>
}