package com.mani.socialapp.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mani.socialapp.ui.MainActivity
import com.mani.socialapp.R
import com.mani.socialapp.ui.posts.comments.CommentsFragment
import com.mani.socialapp.util.updateActionBarTitle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.post_fragment.*

class PostsFragment : Fragment() {

    companion object {
        fun newInstance() = PostsFragment()
    }

    private lateinit var viewModel: PostsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as? MainActivity)?.updateActionBarTitle(R.string.title_posts, false)
        return inflater.inflate(R.layout.post_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(PostsViewModel::class.java)
        viewModel.getAllPosts()

        val adapter = PostsAdapter()
        viewModel.allPosts.observe(viewLifecycleOwner, Observer(adapter::submitList))

        contentRecyclerView.apply {
            this.adapter = adapter
            adapter.onItemClick = {
                val postId = if (it?.id != null) it.id else 1
                CommentsFragment.newInstance(postId).show(childFragmentManager, "CommentsBottomDialogFragment")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as? MainActivity)?.updateActionBarTitle(R.string.app_name, false)
    }
}