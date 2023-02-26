package com.sun.dummyshop.ui.adapter.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.sun.dummyshop.utils.loadImage

@BindingAdapter("app:image")
fun loadImage(imageView: ImageView, url: String?) {
    imageView.loadImage(url)
}
