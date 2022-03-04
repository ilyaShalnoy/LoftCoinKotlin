package com.example.notes.loftcoinkotlin.data.net

import com.example.notes.loftcoinkotlin.core.Mapper
import com.squareup.moshi.Json

data class Listings(
    @Json(name = "data")
    private val data: List<NetworkCoin>
) {
    fun getData(): List<NetworkCoin> {
        return data
    }
}