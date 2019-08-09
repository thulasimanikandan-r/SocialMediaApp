package com.mani.socialapp.ui.posts

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Config
import androidx.paging.toLiveData
import com.mani.socialapp.data.repo.PostRepo
import com.mani.socialapp.util.AndroidDisposable
import io.reactivex.schedulers.Schedulers

class PostsViewModel(app: Application) : AndroidViewModel(app) {

    private val postRepo = PostRepo(app)
    private var disposable = AndroidDisposable()

    val allPosts = postRepo.getAllPostsFromDB().toLiveData(Config(30))
    val allComments = postRepo.getAllCommentsFromDB().toLiveData(Config(30))

    fun getAllPosts() {
        disposable.add(
            postRepo.getAllPostData(1)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe({ posts ->
                    postRepo.insertAllData(posts)
                    Log.d("test", "test$posts")
                }, { error ->
                    error.printStackTrace()
                    Log.e("test", "Post Error->$error.printStackTrace()")
                })
        )
    }

    fun getAllComments(postId :Int) {
        disposable.add(
            postRepo.getAllCommentsByPostId(postId)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe({ comments ->
                    postRepo.insertAllComments(comments)
                    Log.d("test", "test$comments")
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