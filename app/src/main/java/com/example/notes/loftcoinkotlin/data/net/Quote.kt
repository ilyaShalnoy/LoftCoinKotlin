package com.example.notes.loftcoinkotlin.data.net

import com.squareup.moshi.Json

abstract class Quote(
    private val price: Double,
    @Json(name = "percent_change_24h")
    private val change24h: Double
) {

    abstract fun quote(): Map<String, Quote>


    fun price(): Double {
        val iterator: Iterator<Quote> = quote().values.iterator()
        return if (iterator.hasNext())
            iterator.next().price
        else
            0.toDouble()
    }

    // TODO: Cash result function

    fun change24h(): Double {
        val iterator: Iterator<Quote> = quote().values.iterator()
        return if (iterator.hasNext())
            iterator.next().change24h
        else
            0.toDouble()
    }
}
