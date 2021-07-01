package com.ivan.yaskovskyi.fanficapp.activities.Posts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ivan.yaskovskyi.fanficapp.*


class PostsActivity : AppCompatActivity() {
    private val fragmentManager = supportFragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        setNavigation()

//        if(SessionManager(applicationContext).checkUserLogin()){
//
//        }

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.container, PostsFragment()).commit()
    }

    private fun setNavigation() {
        var bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_1 -> {
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.container, PostsFragment()).commit()
                }
//                R.id.page_2 -> {
//                    val fragmentTransaction = fragmentManager.beginTransaction()
//                    fragmentTransaction.replace(R.id.container, PreferredFandomFragment()).commit()
//                }
                R.id.page_3 -> {
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.container, FavouritesFragment()).commit()
                }
//                R.id.page_4 -> {
//                    val fragmentTransaction = fragmentManager.beginTransaction()
//                    fragmentTransaction.replace(R.id.container, UserSettingsFragment()).commit()
//                }
            }
            true
        }
    }


}