package com.mani.socialapp.ui.albums

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Config
import androidx.paging.toLiveData
import com.mani.socialapp.data.repo.AlbumsRepo
import com.mani.socialapp.util.AndroidDisposable
import com.mani.socialapp.util.SharedPreference
import io.reactivex.schedulers.Schedulers

class AlbumsViewModel(app: Application) : AndroidViewModel(app) {

    private val albumRepo = AlbumsRepo(app)
    private var disposable = AndroidDisposable()
    private val userId = SharedPreference(app).getValueInt("userId")

    val allAlbums = albumRepo.getAlbumsByUser(userId).toLiveData(Config(30))
    val allPhotos = albumRepo.getPhotosFromDB().toLiveData(Config(30))

    fun getAlbums() {
        disposable.add(
            albumRepo.getAlbums(userId)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe({ albums ->
                    albumRepo.insertAlbums(albums)
                    Log.d("test", "test$albums")
                }, { error ->
                    error.printStackTrace()
                    Log.e("test", "Post Error->$error.printStackTrace()")
                })
        )
    }

    fun getPhotos(albumId: Int) {
        disposable.add(
            albumRepo.getPhotosByAlbumId(albumId)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe({ photos ->
                    albumRepo.insertPhotos(photos)
                    Log.d("test", "test$photos")
                }, { error ->
                    error.printStackTrace()
                    Log.e("test", "comments Error->$error.printStackTrace()")
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}