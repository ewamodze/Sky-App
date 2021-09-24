package com.ewamo.skyapp.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ewamo.skyapp.data.Photo
import com.ewamo.skyapp.databinding.ItemPhotoBinding
import com.squareup.picasso.Picasso

class PhotoAdapter(
    private val photos: ArrayList<Photo>
) :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    //    private lateinit var binding: ItemPhotoBinding
    var onItemClick: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        val itemView = binding.root

        val itemHolder = PhotoViewHolder(binding)
        itemView.setOnClickListener {
            onItemClick(itemHolder.adapterPosition)
        }

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photos[position]
        photo?.let {
            holder.bind(it)
        }
    }

    inner class PhotoViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {




        fun bind(photo: Photo) {
            binding.apply {

                Picasso.get().load(photo?.url).into(itemImage)
            }
        }

    }

    override fun getItemCount() = photos.size

    companion object {
        private const val PHOTO_KEY = "PHOTO"
    }

}
