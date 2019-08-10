package com.mani.socialapp.data.repo

import android.app.Application
import com.mani.socialapp.data.Api
import com.mani.socialapp.data.AppDatabase
import com.mani.socialapp.data.model.Albums
import com.mani.socialapp.data.model.Photos
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class AlbumsRepo(app: Application) {

    private val albumsDao = AppDatabase.invoke(app).getAlbumsDao()
    private val photosDao = AppDatabase.invoke(app).getPhotosDao()

    fun getAlbums(userId: Int): Observable<MutableList<Albums>> =
        Api.invoke().getAllAlbums(userId)

    fun getAlbumsByUser(userId : Int) = albumsDao.getAllDataByUser(userId)

    fun insertAlbums(albums: MutableList<Albums>) {
        Completable.fromAction {
            albumsDao.insertAll(albums)
        }.subscribeOn(Schedulers.io()).subscribe()
    }

    fun getPhotosByAlbumId(albumId: Int): Observable<MutableList<Photos>> =
        Api.invoke().getPhotosByAlbumId(albumId)

    fun getPhotosFromDB() = photosDao.getAllData()

    fun insertPhotos(photos: MutableList<Photos>) {
        Completable.fromAction {
            photosDao.insertAll(photos)
        }.subscribeOn(Schedulers.io()).subscribe()
    }
}