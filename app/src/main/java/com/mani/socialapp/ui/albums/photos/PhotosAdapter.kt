package com.mani.socialapp.ui.albums.photos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mani.socialapp.R
import com.mani.socialapp.data.model.Photos
import com.squareup.picasso.Picasso
import com.squareup.picasso.PicassoProvider
import kotlinx.android.synthetic.main.photos_fragment.view.*

class PhotosAdapter : PagedListAdapter<Photos, PhotosAdapter.PhotosViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((Photos?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder = PhotosViewHolder(parent)

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Photos>() {
            override fun areItemsTheSame(oldItem: Photos, newItem: Photos): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Photos, newItem: Photos): Boolean = oldItem == newItem
        }
    }

    inner class PhotosViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.photos_fragment, parent, false)
    ) {

        fun bindTo(photos: Photos?) {
            Picasso.get()
                .load(photos?.thumbnailUrl)
                .tag(itemView.context)
                .into(itemView.image)

            itemView.body.text = photos?.title
            itemView.setOnClickListener {
                onItemClick?.invoke(photos)
            }
        }
    }
}