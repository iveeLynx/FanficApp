package com.ivan.yaskovskyi.fanficapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ivan.yaskovskyi.fanficapp.activities.ReadText.ReadTextActivity
import com.ivan.yaskovskyi.fanficapp.adapter.RecyclerViewFavouritesAdapter
import com.ivan.yaskovskyi.fanficapp.model.PostRoom
import com.ivan.yaskovskyi.fanficapp.utils.RoomService
import kotlinx.android.synthetic.main.fragment_favourites.*
import kotlinx.android.synthetic.main.recyclerview_post_item.*

class FavouritesFragment : Fragment() {
    private var recyclerLayoutManager: RecyclerView.LayoutManager? = LinearLayoutManager(activity)
    private var recyclerFavouritesAdapter: RecyclerView.Adapter<RecyclerViewFavouritesAdapter.ViewHolder>? = RecyclerViewFavouritesAdapter{ position -> onListItemClick(position) }


    var favourites: List<PostRoom> = emptyList()

    init{
        RoomService().getFavourites()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view_favourites.apply {
            layoutManager = recyclerLayoutManager
            adapter = recyclerFavouritesAdapter
        }
        if(recyclerFavouritesAdapter!!.itemCount != 0){
            view.findViewById<TextView>(R.id.error_message_fav).text = ""
        }
    }

    private fun onListItemClick(position: Int) {
        val intent = Intent(context, ReadTextActivity::class.java)
        intent.putExtra("name", fanfic_name.text)
        intent.putExtra("text", fanfic_text.text)
        startActivity(intent)
    }
}