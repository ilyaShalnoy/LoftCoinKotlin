package com.example.notes.loftcoinkotlin.data.net

import com.example.notes.loftcoinkotlin.core.Mapper
import com.squareup.moshi.Json

data class Listings(
    @Json(name = "data")
    private val data: List<Coin>
) : Mapper<List<Coin>> {

    override fun to(): List<Coin> {
        return data
    }
}