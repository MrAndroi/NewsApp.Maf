package com.maf.core.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

@BindingAdapter("imageUrl", "radius")
fun ImageView.imageUrl(url: String, radius: Int) {
    Glide.with(this.context).load(url)
        .transform(RoundedCorners(radius))
        .dontAnimate()
        .into(this)
}