package com.ivan.yaskovskyi.fanficapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ivan.yaskovskyi.fanficapp.model.PostRoom

@Dao
interface PostDao {
    @Query("SELECT * FROM postroom")
    fun getAll(): List<PostRoom>
    @Insert
    fun insertAll(vararg posts: PostRoom)
    @Insert
    fun insertPost(post:PostRoom)
    @Delete
    fun delete(posts: PostRoom)
}
