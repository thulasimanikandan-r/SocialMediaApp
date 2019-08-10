package com.mani.socialapp.ui.albums

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
import com.mani.socialapp.ui.albums.photos.PhotosFragment
import com.mani.socialapp.util.inTransaction
import com.mani.socialapp.util.updateActionBarTitle
import kotlinx.android.synthetic.main.post_fragment.*


class AlbumsFragment : Fragment() {

    companion object {
        fun newInstance() = AlbumsFragment()
    }

    private lateinit var viewModel: AlbumsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as? MainActivity)?.updateActionBarTitle(R.string.title_album, false)
        return inflater.inflate(R.layout.post_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(AlbumsViewModel::class.java)
        viewModel.getAlbums()

        val adapter = AlbumsAdapter()
        viewModel.allAlbums.observe(this, Observer(adapter::submitList))
        contentRecyclerView.apply {
            this.adapter = adapter
            adapter.onItemClick = {
                val albumId = if (it?.id != null) it.id else 1

                fragmentManager?.inTransaction {
                    replace(
                        R.id.fragment, PhotosFragment.newInstance(albumId),
                        "PhotosFragment"
                    ).addToBackStack("AlbumsFragment")
                }
                Toast.makeText(this@AlbumsFragment.context, "inside${it?.id}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as? MainActivity)?.updateActionBarTitle(R.string.app_name, false)
    }
}