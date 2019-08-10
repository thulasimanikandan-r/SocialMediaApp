package com.mani.socialapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mani.socialapp.data.dao.AlbumsDao
import com.mani.socialapp.data.dao.CommentsDao
import com.mani.socialapp.data.dao.PhotosDao
import com.mani.socialapp.data.dao.PostDao
import com.mani.socialapp.data.model.Albums
import com.mani.socialapp.data.model.Photos
import com.mani.socialapp.data.model.Comments
import com.mani.socialapp.data.model.Posts

@Database(
    entities = [Posts::class, Comments::class, Albums::class, Photos::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPostsDao(): PostDao

    abstract fun getCommentsDao(): CommentsDao

    abstract fun getAlbumsDao(): AlbumsDao

    abstract fun getPhotosDao(): PhotosDao

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

