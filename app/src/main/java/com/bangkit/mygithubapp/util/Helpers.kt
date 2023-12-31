package com.bangkit.mygithubapp.util

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

fun showToast(text: String, context: Context) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

fun ImageView.loadImage(url: String) {
    Glide
        .with(this.context)
        .load(url)
        .into(this)
}

fun ImageView.loadCircleImage(url: String) {
    Glide
        .with(this.context)
        .load(url)
        .circleCrop()
        .into(this)
}