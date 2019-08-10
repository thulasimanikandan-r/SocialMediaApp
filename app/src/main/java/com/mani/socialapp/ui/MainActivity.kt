package com.mani.socialapp.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mani.socialapp.R
import com.mani.socialapp.ui.albums.AlbumsFragment
import com.mani.socialapp.ui.posts.PostsFragment
import com.mani.socialapp.ui.users.UserDetailsFragment
import com.mani.socialapp.ui.users.UsersFragment
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
                addFragment(R.id.fragment, UserDetailsFragment.newInstance(), "UsersDetailsFragment")
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.navigation_user -> {
                UsersFragment.newInstance().show(this.supportFragmentManager, "UserBottomDialogFragment")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

