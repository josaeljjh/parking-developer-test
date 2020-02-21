package com.prueba.hugo.utils.extensions

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.prueba.hugo.R


//Fondos de servicio
@SuppressLint("CheckResult")
fun ImageView.setBackgroundUrl(id: String) {
    try {
        val options = RequestOptions()
            .placeholder(R.mipmap.ic_launcher)
            .priority(Priority.NORMAL)
            .format(DecodeFormat.PREFER_RGB_565)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .dontAnimate()
            .fitCenter()

        Glide.with(this)
            .asBitmap()
            .load(id)
            .apply(options)
            .into(this)
    } catch (e: Exception) {
    }
}

@BindingAdapter("image")
fun ImageView.setBackground(id: Int) {
    try {
        val options = RequestOptions()
            .placeholder(R.mipmap.ic_launcher)
            .priority(Priority.NORMAL)
            .format(DecodeFormat.PREFER_RGB_565)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .dontAnimate()

        Glide.with(this)
            .asBitmap()
            .load(id)
            .apply(options)
            .into(this)
    } catch (e: Exception) {
    }
}