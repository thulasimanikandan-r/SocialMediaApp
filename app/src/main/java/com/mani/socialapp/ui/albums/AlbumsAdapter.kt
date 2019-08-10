package com.mani.socialapp.ui.albums

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mani.socialapp.R
import com.mani.socialapp.data.model.Albums

class AlbumsAdapter : PagedListAdapter<Albums, AlbumsAdapter.AlbumsViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((Albums?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder = AlbumsViewHolder(parent)

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Albums>() {
            override fun areItemsTheSame(oldItem: Albums, newItem: Albums): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Albums, newItem: Albums): Boolean = oldItem == newItem
        }
    }

    inner class AlbumsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.content_recycler_view, parent, false)
    ) {

        fun bindTo(albums: Albums?) {
            itemView.findViewById<TextView>(R.id.title).text = albums?.title
            itemView.findViewById<TextView>(R.id.body).visibility = View.GONE
            itemView.setOnClickListener {
                onItemClick?.invoke(albums)
            }
        }
    }
}