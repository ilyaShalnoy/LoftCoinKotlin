package com.example.notes.loftcoinkotlin.core.util

import android.widget.ImageView

interface ImageLoader {

    fun load(url: String): ImageRequest

    interface ImageRequest {
        fun into(view: ImageView)
    }

}