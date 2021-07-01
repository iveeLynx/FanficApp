package com.ivan.yaskovskyi.fanficapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ivan.yaskovskyi.fanficapp.R
import com.ivan.yaskovskyi.fanficapp.model.Post
import com.ivan.yaskovskyi.fanficapp.model.PostRoom
import com.ivan.yaskovskyi.fanficapp.utils.JsonUtil
import com.ivan.yaskovskyi.fanficapp.utils.RoomService
import com.ivan.yaskovskyi.fanficapp.utils.SaveClickListener
import com.squareup.picasso.Picasso
import java.lang.ref.WeakReference


class RecyclerViewPostsAdapter(private val onItemClicked: (position: Int) -> Unit, preferred:Boolean) :
        RecyclerView.Adapter<RecyclerViewPostsAdapter.ViewHolder>() {
    var posts: List<Post> = emptyList()
    var imageUrl: String = "https://res.cloudinary.com/dqqr510iw/image/upload/images/"

    init {
        try {
            posts = JsonUtil.instance.getPosts()
        } catch (msg: Exception) {
            Log.e("Connection", "Error")
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.recyclerview_post_item, viewGroup, false)
        return ViewHolder(view, onItemClicked)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.fanficName.text = posts[position].fName
        Picasso.get().load(imageUrl + posts[position].fImage).into(viewHolder.fanficImage)
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
        val saveButton: Button

        init {
            itemView.setOnClickListener(this)
            saveButton = view.findViewById(R.id.save_favorites)
            fanficName = view.findViewById(R.id.fanfic_name)
            fanficImage = view.findViewById(R.id.fanfic_image)
            fanficFandom = view.findViewById(R.id.fanfic_fandom)
            fanficDescription = view.findViewById(R.id.fanfic_description)
            fanficTags = view.findViewById(R.id.fanfic_tags)
            fanficDate = view.findViewById(R.id.fanfic_creation_date)
            fanficText = view.findViewById(R.id.fanfic_text)
            saveButton.setOnClickListener {

                val bytesImage = RoomService().imageToByte(fanficImage)
                RoomService().savePost(PostRoom( 0,
                        fanficName.text.toString(),
                        fanficFandom.text.toString(),
                        fanficDescription.text.toString(),
                        fanficTags.text.toString(),
                        fanficDate.text.toString(),
                        fanficText.text.toString(),
                        bytesImage))
                saveButton.text = "Saved"
            }
        }

        override fun onClick(v: View) {
            val position = adapterPosition
            onItemClicked(position)
        }

    }
}