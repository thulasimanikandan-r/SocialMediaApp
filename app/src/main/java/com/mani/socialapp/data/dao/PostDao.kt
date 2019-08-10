package com.mani.socialapp.data.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mani.socialapp.data.model.Posts
import io.reactivex.Single

@Dao
interface PostDao {

    @Query("Select * from Posts where id =:id")
    fun get(id: Int): Single<Posts>

    @Query("Select * from Posts")
    fun getAllData(): DataSource.Factory<Int, Posts>

    @Query("Select * from Posts where userId =:userId")
    fun getAllDataByUser(userId: Int): DataSource.Factory<Int, Posts>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: Posts)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: MutableList<Posts>)
}