package com.mani.socialapp.ui.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mani.socialapp.R
import com.mani.socialapp.data.model.Posts

class PostsAdapter :PagedListAdapter<Posts, PostsAdapter.PostViewHolder>(DIFF_CALLBACK){

    var onItemClick: ((Posts?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder  = PostViewHolder(parent)

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    companion object{

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Posts>(){
            override fun areItemsTheSame(oldItem: Posts, newItem: Posts): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Posts, newItem: Posts): Boolean = oldItem == newItem
        }
    }

    inner class PostViewHolder(parent:ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.content_recycler_view, parent, false)){

        fun bindTo(posts: Posts?) {
            itemView.findViewById<TextView>(R.id.title).text = posts?.title
            itemView.findViewById<TextView>(R.id.body).text = posts?.body
            itemView.setOnClickListener {
                onItemClick?.invoke(posts)
            }
        }
    }
}