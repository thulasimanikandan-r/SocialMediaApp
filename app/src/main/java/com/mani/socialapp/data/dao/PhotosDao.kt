package com.mani.socialapp.data.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mani.socialapp.data.model.Photos
import io.reactivex.Single

@Dao
interface PhotosDao {

    @Query("Select * from Photos where id =:id")
    fun get(id: Int): Single<Photos>

    @Query("Select * from Photos")
    fun getAllData(): DataSource.Factory<Int, Photos>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(Photos: Photos)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(Photos: MutableList<Photos>)
}