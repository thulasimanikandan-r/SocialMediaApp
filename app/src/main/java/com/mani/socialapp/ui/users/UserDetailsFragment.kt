package com.mani.socialapp.ui.users


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.mani.socialapp.R
import com.mani.socialapp.ui.MainActivity
import com.mani.socialapp.util.getEditableData
import com.mani.socialapp.util.updateActionBarTitle
import kotlinx.android.synthetic.main.fragment_user_details.*

class UserDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = UserDetailsFragment()
    }

    private lateinit var viewModel: UsersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as? MainActivity)?.updateActionBarTitle(R.string.title_user, false)
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UsersViewModel::class.java)

        viewModel.user.observe(this, Observer {
            if (it != null) {
                name.editText?.text = getEditableData(it.name)
                email.editText?.text = getEditableData(it.email)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as? MainActivity)?.updateActionBarTitle(R.string.app_name, false)
    }
}
