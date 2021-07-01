package com.ivan.yaskovskyi.fanficapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ivan.yaskovskyi.fanficapp.activities.ReadText.ReadTextActivity
import com.ivan.yaskovskyi.fanficapp.adapter.RecyclerViewPostsAdapter
import kotlinx.android.synthetic.main.fragment_posts.*
import kotlinx.android.synthetic.main.recyclerview_post_item.*


class PostsFragment : Fragment() {
    private var recyclerLayoutManager: RecyclerView.LayoutManager? = LinearLayoutManager(activity)
    private var recyclerPostsAdapter: RecyclerView.Adapter<RecyclerViewPostsAdapter.ViewHolder>? = RecyclerViewPostsAdapter( { position -> onListItemClick(position) }, true)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var v: View = inflater.inflate(R.layout.fragment_posts, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view_posts.apply {
            layoutManager = recyclerLayoutManager
            adapter = recyclerPostsAdapter
        }
        Handler(Looper.getMainLooper()).postDelayed({
            checkView(view)
        }, 5000)
    }

    private fun checkView(view: View) {
        if (recyclerPostsAdapter!!.itemCount == 0) {
            view.findViewById<TextView>(R.id.error_message).visibility = View.VISIBLE
            view.findViewById<ImageView>(R.id.error_image).visibility = View.VISIBLE
        }
        view.findViewById<ProgressBar>(R.id.progress_bar).visibility = View.GONE
        view.findViewById<RecyclerView>(R.id.recycler_view_posts).visibility = View.VISIBLE
    }

    private fun onListItemClick(position: Int) {
        val intent = Intent(context, ReadTextActivity::class.java)
        intent.putExtra("name", fanfic_name.text)
        intent.putExtra("text", fanfic_text.text)
        startActivity(intent)
    }


}