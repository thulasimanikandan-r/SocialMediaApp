package com.mani.socialapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    val userId: Int,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val email: String
)