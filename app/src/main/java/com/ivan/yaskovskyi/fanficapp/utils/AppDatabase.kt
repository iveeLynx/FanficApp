package com.ivan.yaskovskyi.fanficapp.utils

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ivan.yaskovskyi.fanficapp.dao.PostDao
import com.ivan.yaskovskyi.fanficapp.model.PostRoom

@Database(entities = arrayOf(PostRoom::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao

}
