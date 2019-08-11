package com.mani.socialapp.ui.albums.photos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.mani.socialapp.R
import com.mani.socialapp.ui.MainActivity
import com.mani.socialapp.ui.albums.AlbumsViewModel
import com.mani.socialapp.ui.albums.photos.PhotoViewerActivity.Companion.PHOTO_VIEW_ID
import com.mani.socialapp.util.updateActionBarTitle
import kotlinx.android.synthetic.main.post_fragment.*


class PhotosFragment : Fragment() {

    companion object {
        const val ALBUM_ID = "ALBUM_ID"

        fun newInstance(albumId: Int) = PhotosFragment().apply {
            arguments = Bundle().apply {
                putInt(ALBUM_ID, albumId)
            }
        }
    }

    private lateinit var viewModel: AlbumsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as? MainActivity)?.updateActionBarTitle(R.string.title_photos, true)
        return inflater.inflate(R.layout.post_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(AlbumsViewModel::class.java)
        val albumId = if (arguments?.getInt(ALBUM_ID) != null)
            arguments?.getInt(ALBUM_ID)!! else 1
        viewModel.getPhotos(albumId)

        val adapter = PhotosAdapter()
        viewModel.allPhotos.observe(viewLifecycleOwner, Observer(adapter::submitList))
        contentRecyclerView.apply {
            this.adapter = adapter
            layoutManager = GridLayoutManager(this@PhotosFragment.context, 2)
            adapter.onItemClick = {
                val intent = Intent(activity, PhotoViewerActivity::class.java)
                intent.putExtra(PHOTO_VIEW_ID, it?.url)
                startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as? MainActivity)?.updateActionBarTitle(R.string.app_name, false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                (activity as? MainActivity)?.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}