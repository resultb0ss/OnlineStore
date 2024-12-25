package com.example.onlinestore.Utilits

import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide


fun ImageView.loadImage(url: String) {
    Glide.with(this.context).load(url).into(this)
}

fun Fragment.myToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}