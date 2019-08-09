package com.mani.socialapp.ui.posts.comments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mani.socialapp.R
import com.mani.socialapp.data.model.Comments

class CommentsAdapter : PagedListAdapter<Comments, CommentsAdapter.CommentsViewHolder>(
    DIFF_CALLBACK
) {

    var onItemClick: ((Comments?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder = CommentsViewHolder(parent)

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Comments>() {
            override fun areItemsTheSame(oldItem: Comments, newItem: Comments): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Comments, newItem: Comments): Boolean = oldItem == newItem
        }
    }

    inner class CommentsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.content_recycler_view, parent, false)
    ) {

        fun bindTo(Comments: Comments?) {
            itemView.findViewById<TextView>(R.id.title).text = Comments?.name
            itemView.findViewById<TextView>(R.id.body).text = Comments?.body
            itemView.setOnClickListener {
                onItemClick?.invoke(Comments)
            }
        }
    }
}