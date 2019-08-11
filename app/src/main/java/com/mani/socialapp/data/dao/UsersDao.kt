package com.mani.socialapp.data.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mani.socialapp.data.model.User

@Dao
interface UsersDao {

    @Query("Select * from User where id =:id")
    fun get(id: Int): LiveData<User>

    @Query("Select * from User")
    fun getAllData(): DataSource.Factory<Int, User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(Users: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(Users: MutableList<User>)
}