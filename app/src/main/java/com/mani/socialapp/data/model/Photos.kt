package com.mani.socialapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Photos(
    val albumId: Int,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)