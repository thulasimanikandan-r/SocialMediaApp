package com.mani.socialapp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mani.socialapp.R
import com.mani.socialapp.data.model.User

class UsersAdapter : PagedListAdapter<User, UsersAdapter.UserViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((User?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder = UserViewHolder(parent)

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem
        }
    }

    inner class UserViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.content_recycler_view, parent, false)
    ) {

        fun bindTo(user: User?) {
            itemView.findViewById<TextView>(R.id.title).text = user?.name
            itemView.findViewById<TextView>(R.id.body).text = user?.email
            itemView.setOnClickListener {
                onItemClick?.invoke(user)
            }
        }
    }
}