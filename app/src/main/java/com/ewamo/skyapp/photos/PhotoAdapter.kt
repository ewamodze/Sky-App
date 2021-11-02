package com.ewamo.skyapp.photos

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ewamo.skyapp.data.Photo
import com.ewamo.skyapp.databinding.ItemPhotoBinding

class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    var onItemClick: (Int) -> Unit = {}

    var photos: ArrayList<Photo> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photos[position]
        photo?.let {
            holder.bind(it)
        }
        holder.itemView.setOnClickListener {
            Log.i("MyTag", "position = $position")
            onItemClick(position)
        }
    }

    inner class PhotoViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: Photo) {
            binding.apply {
                data = photo
            }
        }

    }

    override fun getItemCount() = photos.size

    companion object {
        private const val PHOTO_KEY = "PHOTO"
    }

}
