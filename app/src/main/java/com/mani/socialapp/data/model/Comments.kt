package com.mani.socialapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Comments(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val postId: Int = 1,
    val name: String,
    val email: String,
    val body: String

)