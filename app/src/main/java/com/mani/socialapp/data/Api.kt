package com.mani.socialapp.data

import com.mani.socialapp.data.model.Albums
import com.mani.socialapp.data.model.Comments
import com.mani.socialapp.data.model.Photos
import com.mani.socialapp.data.model.Posts
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  Thulasimanikandan
 */
interface Api {

    @GET("posts")
    fun getAllPosts(@Query("userId") userId: Int): Observable<MutableList<Posts>>

    @GET("comments")
    fun getAllCommentsByPostId(@Query("postId") postId: Int): Observable<MutableList<Comments>>

    @GET("albums")
    fun getAllAlbums(@Query("userId") userId: Int): Observable<MutableList<Albums>>

    @GET("photos")
    fun getPhotosByAlbumId(@Query("albumId") albumId: Int): Observable<MutableList<Photos>>

    companion object {
        operator fun invoke(): Api {
            return Retrofit
                .Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }
}