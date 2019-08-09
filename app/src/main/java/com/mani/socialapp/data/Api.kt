package com.mani.socialapp.data

import com.mani.socialapp.data.model.Comments
import com.mani.socialapp.data.model.Posts
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

/**
 *  Thulasimanikandan
 */
interface Api {


    @GET("posts")
    fun getAllPosts(@Query("userId") userId: Int): Observable<MutableList<Posts>>

    @GET("comments")
    fun getAllCommentsByPostId(@Query("postId") postId: Int): Observable<MutableList<Comments>>

    /* @GET("posts/{postId}/comments")
     fun getAllCommentsByPostId(@Path("postId") postId: Int): Observable<MutableList<Comments>>*/

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