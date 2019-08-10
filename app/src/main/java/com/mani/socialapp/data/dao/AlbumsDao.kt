package com.mani.socialapp.data.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mani.socialapp.data.model.Albums
import io.reactivex.Single

@Dao
interface AlbumsDao {

    @Query("Select * from Albums where id =:id")
    fun get(id: Int): Single<Albums>

    @Query("Select * from Albums")
    fun getAllData(): DataSource.Factory<Int, Albums>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(Albums: Albums)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(Albums: MutableList<Albums>)
}