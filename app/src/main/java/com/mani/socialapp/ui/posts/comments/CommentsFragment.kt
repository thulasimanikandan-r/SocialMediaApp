package com.mani.socialapp.ui.posts.comments

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mani.socialapp.R
import com.mani.socialapp.ui.posts.PostsViewModel

class CommentsFragment : BottomSheetDialogFragment() {

    companion object {
        const val POST_ID = "POST_ID"

        fun newInstance(postId: Int) = CommentsFragment().apply {
            arguments = Bundle().apply {
                putInt(POST_ID, postId)
            }
        }
    }

    private lateinit var viewModel: PostsViewModel
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        val view = View.inflate(context, R.layout.bottom_sheet_fragment, null)
        val postId = arguments?.getInt(POST_ID)!!

        viewModel = ViewModelProviders.of(this).get(PostsViewModel::class.java)
        viewModel.getAllComments(postId)

        val adapter = CommentsAdapter()
        viewModel.allComments.observe(this, Observer(adapter::submitList))

        val recyclerView = view.findViewById<RecyclerView>(R.id.contentRecyclerView)
        recyclerView.apply {
            this.adapter = adapter
        }

        dialog.setContentView(view)
        bottomSheetBehavior = BottomSheetBehavior.from(view.parent as View)
        return dialog
    }

    override fun onStart() {
        super.onStart()
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }
}