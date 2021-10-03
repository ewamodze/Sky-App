package com.ewamo.skyapp.ui.photodetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ewamo.skyapp.R
import com.ewamo.skyapp.databinding.FragmentDetailPhotoBinding
import com.squareup.picasso.Picasso

class PhotoDetailFragment : Fragment(R.layout.fragment_detail_photo) {

    val args: PhotoDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailPhotoBinding.bind(view)

        val picture = args.photo?.url
        Picasso.get().load(picture).into(binding.photoImageView)

        val text = args.photo?.explanation
        binding.photoDescription.text = text

        binding.apply {
            //todo
        }
    }

}
