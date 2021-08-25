package com.ewamo.skyapp.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.ewamo.skyapp.data.Photo
import com.ewamo.skyapp.databinding.ItemPhotoBinding

class PhotoAdapter(
    private val photos: ArrayList<Photo>,
    private val listener: AdapterView.OnItemClickListener
) :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

//    private lateinit var binding: ItemPhotoBinding
    var onItemClick: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

//        val itemView = binding.root
//
//        val itemHolder = PhotoViewHolder(itemView)
//        itemView.setOnClickListener {
//            onItemClick(itemHolder.adapterPosition)
//        }

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

        init {
            binding.apply {
                val position = adapterPosition
//                if (position != RecyclerView.NO_POSITION) {
//                    val photo = getItem(position)
//                    listener.onItemClick(photo)
//                }
            }
            //todo
        }

        fun bind(photo: Photo) {
            binding.apply {
                //todo
            }
        }

    }

    override fun getItemCount() = photos.size

    companion object {
        private const val PHOTO_KEY = "PHOTO"
    }

}
