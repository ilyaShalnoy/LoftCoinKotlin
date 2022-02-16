package com.example.notes.loftcoinkotlin.data.net

import com.squareup.moshi.Json

data class Coin(
    @Json(name = "id")
    private val id: Int,
    @Json(name = "name")
    private val name: String,
    @Json(name = "symbol")
    private val symbol: String,
    @Json(name = "cmc_rank")
    private val rank: Int,
    @Json(name = "quote")
    private val quote: Map<String, Quote>
) {
    fun getId() = id
    fun getSymbol() = symbol

    fun price(): Double {
        val iterator: Iterator<Quote> = quote.values.iterator()
        return if (iterator.hasNext())
            iterator.next().getPrice()
        else
            0.toDouble()
    }

    fun change24h(): Double {
        val iterator: Iterator<Quote> = quote.values.iterator()
        return if (iterator.hasNext())
            iterator.next().getChange()
        else
            0.toDouble()
    }
}

