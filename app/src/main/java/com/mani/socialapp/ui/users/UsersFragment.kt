package com.mani.socialapp.ui.users

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mani.socialapp.R
import com.mani.socialapp.util.SharedPreference
import kotlinx.android.synthetic.main.bottom_sheet_fragment.view.*

class UsersFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private lateinit var viewModel: UsersViewModel
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        val view = View.inflate(context, R.layout.bottom_sheet_fragment, null)
        val sharedPreference = SharedPreference(context!!)

        viewModel = ViewModelProviders.of(this).get(UsersViewModel::class.java)
        viewModel.getAllUsers()

        val adapter = UsersAdapter()
        viewModel.allUsers.observe(this, Observer(adapter::submitList))

        view.title.text = getString(R.string.title_select_user)
        view.contentRecyclerView.apply {
            this.adapter = adapter
            adapter.onItemClick = {
                sharedPreference.save("userId", it?.id!!)
                dismiss()
            }
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