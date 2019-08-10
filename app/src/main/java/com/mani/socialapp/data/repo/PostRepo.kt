package com.mani.socialapp.data.repo

import android.app.Application
import com.mani.socialapp.data.Api
import com.mani.socialapp.data.AppDatabase
import com.mani.socialapp.data.model.Comments
import com.mani.socialapp.data.model.Posts
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class PostRepo(app: Application) {

    private val postDao = AppDatabase.invoke(app).getPostsDao()
    private val commentsDao = AppDatabase.invoke(app).getCommentsDao()

    fun getAllPostData(userId: Int): Observable<MutableList<Posts>> =
        Api.invoke().getAllPosts(userId)

    fun getAllPostsByUser(userId: Int) = postDao.getAllDataByUser(userId)

    fun insertAllData(posts: MutableList<Posts>) {
        Completable.fromAction {
            postDao.insertAll(posts)
        }.subscribeOn(Schedulers.io()).subscribe()
    }


    fun getAllCommentsFromDB() = commentsDao.getAllData()

    fun getAllCommentsByPostId(postId: Int): Observable<MutableList<Comments>> =
        Api.invoke().getAllCommentsByPostId(postId)

    fun insertAllComments(comments: MutableList<Comments>) {
        Completable.fromAction {
            commentsDao.insertAll(comments)
        }.subscribeOn(Schedulers.io()).subscribe()
    }
}