package com.mani.socialapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mani.socialapp.data.dao.*
import com.mani.socialapp.data.model.*

@Database(
    entities = [Posts::class, Comments::class, Albums::class, Photos::class, User::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPostsDao(): PostDao

    abstract fun getCommentsDao(): CommentsDao

    abstract fun getAlbumsDao(): AlbumsDao

    abstract fun getPhotosDao(): PhotosDao

    abstract fun getUsersDao(): UsersDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
                instance
                    ?: buildDatabase(context).also {
                        instance = it
                    }
            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "SocialMediaApp"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}

