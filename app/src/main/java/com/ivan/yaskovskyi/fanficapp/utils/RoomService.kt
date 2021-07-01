package com.ivan.yaskovskyi.fanficapp.utils

import android.R
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.widget.ImageView
import androidx.room.Room
import com.ivan.yaskovskyi.fanficapp.model.PostRoom
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream


class RoomService() {
    var db: AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "postroom.db"
    ).build()

    companion object {
        private lateinit var context: Context
        var postsRoom: List<PostRoom> = emptyList()

        fun setContext(appContext: Context) {
            context = appContext
        }
    }

    fun savePost(post:PostRoom) {
        GlobalScope.launch {
            try {
                db.postDao().insertPost(post)
            } catch (msg:Exception){
                Log.e("Sql",msg.toString())
            }
        }
    }

    fun getFavourites() {
        GlobalScope.launch {
            postsRoom = db.postDao().getAll()
            db.close()
        }
    }

    fun byteToImage(imageView: ImageView, byteArray: ByteArray):Bitmap  {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
    fun imageToByte(imageView: ImageView): ByteArray {
        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        return baos.toByteArray()
    }

}