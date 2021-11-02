package com.ewamo.skyapp.data

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imgUrl")
fun bindMyImage(imgView: ImageView, imgUrl: String?){
    Picasso.get().load(imgUrl).into(imgView)
}