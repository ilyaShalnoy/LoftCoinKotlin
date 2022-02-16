package com.example.notes.loftcoinkotlin.data.net

import com.squareup.moshi.Json

data class Quote(
    private val price: Double,
    @Json(name = "percent_change_24h")
    private val change24h: Double
) {

    fun getPrice() = price
    fun getChange() = change24h

}
