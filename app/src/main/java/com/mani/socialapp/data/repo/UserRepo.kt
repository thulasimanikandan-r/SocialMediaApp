package com.mani.socialapp.data.repo

import android.app.Application
import com.mani.socialapp.data.Api
import com.mani.socialapp.data.AppDatabase
import com.mani.socialapp.data.model.User
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class UserRepo(app: Application) {

    private val userDao = AppDatabase.invoke(app).getUsersDao()

    fun getAllData(): Observable<MutableList<User>> =
        Api.invoke().getAllUsers()

    fun getAllDataFromDB() = userDao.getAllData()

    fun getAllDataById(userId: Int) = userDao.get(userId)

    fun insertAllData(users: MutableList<User>) {
        Completable.fromAction {
            userDao.insertAll(users)
        }.subscribeOn(Schedulers.io()).subscribe()
    }
}