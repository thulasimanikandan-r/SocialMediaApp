package com.mani.socialapp.ui.albums.photos

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.mani.socialapp.R
import com.mani.socialapp.util.updateActionBarTitle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_photo_viewer.*

class PhotoViewerActivity : AppCompatActivity() {

    companion object {
        const val PHOTO_VIEW_ID = "PHOTO_VIEW_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_viewer)

        updateActionBarTitle(R.string.title_photo_view, true)

        Picasso.get()
            .load(intent.getStringExtra(PHOTO_VIEW_ID))
            .tag(this)
            .into(photoView)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
