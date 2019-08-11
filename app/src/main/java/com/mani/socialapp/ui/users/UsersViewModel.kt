package com.mani.socialapp.ui.users

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Config
import androidx.paging.toLiveData
import com.mani.socialapp.data.model.User
import com.mani.socialapp.data.repo.UserRepo
import com.mani.socialapp.util.AndroidDisposable
import com.mani.socialapp.util.SharedPreference
import io.reactivex.schedulers.Schedulers

class UsersViewModel(app: Application) : AndroidViewModel(app) {

    private val userRepo = UserRepo(app)
    private var disposable = AndroidDisposable()
    private val userId = SharedPreference(app).getValueInt("userId")

    val allUsers = userRepo.getAllDataFromDB().toLiveData(Config(30))
    var user = userRepo.getAllDataById(userId)

    fun getAllUsers() {
        disposable.add(
            userRepo.getAllData()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe({ users ->
                    userRepo.insertAllData(users)
                    Log.d("test", "test$users")
                }, { error ->
                    error.printStackTrace()
                    Log.e("test", "Post Error->$error.printStackTrace()")
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}