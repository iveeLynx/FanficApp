package com.ivan.yaskovskyi.fanficapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ivan.yaskovskyi.fanficapp.R
import com.ivan.yaskovskyi.fanficapp.model.PostRoom
import com.ivan.yaskovskyi.fanficapp.utils.RoomService
import java.lang.Exception


class RecyclerViewFavouritesAdapter(private val onItemClicked: (position: Int) -> Unit) :
        RecyclerView.Adapter<RecyclerViewFavouritesAdapter.ViewHolder>() {
    var posts: List<PostRoom> = emptyList()

    init {
        try {
            posts = RoomService.postsRoom
        } catch (msg: Exception) {
            Log.e("Connection", "Error")
        }
    }



    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.recyclerview_post_item, viewGroup, false)
        view.findViewById<Button>(R.id.save_favorites).visibility = View.GONE
        return ViewHolder(view, onItemClicked)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.fanficName.text = posts[position].fName
        viewHolder.fanficImage.setImageBitmap(RoomService().byteToImage(viewHolder.fanficImage, posts[position].fImage))
        viewHolder.fanficFandom.text = posts[position].fFandom
        viewHolder.fanficDescription.text = posts[position].fDescription
        viewHolder.fanficTags.text = posts[position].fTags
        viewHolder.fanficDate.text = posts[position].fDate
        viewHolder.fanficText.text = posts[position].fText
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    inner class ViewHolder(view: View, private val onItemClicked: (position: Int) -> Unit) : RecyclerView.ViewHolder(view), View.OnClickListener {

        var fanficName: TextView
        val fanficImage: ImageView
        val fanficFandom: TextView
        val fanficDescription: TextView
        val fanficTags: TextView
        val fanficDate: TextView
        val fanficText: TextView
        init {
            itemView.setOnClickListener(this)
            fanficName = view.findViewById(R.id.fanfic_name)
            fanficImage = view.findViewById(R.id.fanfic_image)
            fanficFandom = view.findViewById(R.id.fanfic_fandom)
            fanficDescription = view.findViewById(R.id.fanfic_description)
            fanficTags = view.findViewById(R.id.fanfic_tags)
            fanficDate = view.findViewById(R.id.fanfic_creation_date)
            fanficText = view.findViewById(R.id.fanfic_text)
        }

        override fun onClick(v: View) {
            val position = adapterPosition
            onItemClicked(position)
        }

    }
}