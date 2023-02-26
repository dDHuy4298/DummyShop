package com.sun.dummyshop.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.sun.dummyshop.R

fun ImageView.loadImage(url: String?) {
    Glide.with(context)
        .load(url)
        .error(R.drawable.error)
        .placeholder(R.drawable.loading)
        .into(this)
}
