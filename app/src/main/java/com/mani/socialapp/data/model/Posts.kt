package com.mani.socialapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Posts(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val userId: Int = 1,
    val title: String,
    val body: String
)