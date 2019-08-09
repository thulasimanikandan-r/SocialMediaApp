package com.mani.socialapp.data.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mani.socialapp.data.model.Comments
import io.reactivex.Single

@Dao
interface CommentsDao {

    @Query("Select * from Comments where id =:id")
    fun get(id: Int): Single<Comments>

    @Query("Select * from Comments")
    fun getAllData(): DataSource.Factory<Int, Comments>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(Comments: Comments)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(Comments: MutableList<Comments>)
}