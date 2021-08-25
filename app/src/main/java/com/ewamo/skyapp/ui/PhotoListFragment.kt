package com.ewamo.skyapp.ui

import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ewamo.skyapp.R
import com.ewamo.skyapp.data.Photo
import com.ewamo.skyapp.databinding.FragmentPhotoListBinding
import com.ewamo.skyapp.photos.PhotoAdapter
import com.ewamo.skyapp.photos.PhotoViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_photo.*

class PhotoListFragment : Fragment(R.layout.fragment_photo_list) {

    private val viewModel: PhotoViewModel by viewModels()
    private var selectedPhoto: Photo? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

//        selectedPhoto = intent.getSerializableExtra(PHOTO_KEY) as Photo
//        Picasso.with(this).load(selectedPhoto?.url).into(photoImageView)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentPhotoListBinding.bind(view)

        val photoAdapter = PhotoAdapter(this)

        binding.apply {
            recyclerViewPhotos.apply {
                adapter = photoAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
        initializeAdapter(photoData)
    }

        private fun initializeAdapter(photoData: ArrayList<Photo>) {

        val adapter = PhotoAdapter(photoData)
        adapter.onItemClick = { position ->
            selectedPhoto = position



    }}

}
