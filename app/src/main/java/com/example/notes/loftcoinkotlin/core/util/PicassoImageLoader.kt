package com.example.notes.loftcoinkotlin.core.util

import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PicassoImageLoader @Inject constructor(private val picasso: Picasso) : ImageLoader {

    override fun load(url: String): ImageLoader.ImageRequest {
        return PicassoImageRequest(picasso.load(url))
    }

    inner class PicassoImageRequest(private val request: RequestCreator) : ImageLoader.ImageRequest {

        override fun into(view: ImageView) {
            request.into(view)
        }

    }
}