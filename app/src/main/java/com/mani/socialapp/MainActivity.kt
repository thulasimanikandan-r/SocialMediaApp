package com.mani.socialapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mani.socialapp.ui.albums.AlbumsFragment
import com.mani.socialapp.ui.posts.PostsFragment
import com.mani.socialapp.util.addFragment

class MainActivity : AppCompatActivity() {

    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_posts -> {
                addFragment(R.id.fragment, PostsFragment.newInstance(), "PostsFragment")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_albums -> {
                addFragment(R.id.fragment, AlbumsFragment.newInstance(), "AlbumsFragment")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_user_details -> {

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        textMessage = findViewById(R.id.message)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        navView.selectedItemId = R.id.navigation_posts
    }
}
