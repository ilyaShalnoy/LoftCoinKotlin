package com.example.notes.loftcoinkotlin.data.net

import com.example.notes.loftcoinkotlin.core.Mapper
import com.example.notes.loftcoinkotlin.data.CoinsDataModel
import com.squareup.moshi.Json

data class NetworkCoin(
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
) : Mapper<CoinsDataModel> {

    private fun price(): Double {
        val iterator: Iterator<Quote> = quote.values.iterator()
        return if (iterator.hasNext())
            iterator.next().getPrice()
        else
            0.toDouble()
    }

    private fun change24h(): Double {
        val iterator: Iterator<Quote> = quote.values.iterator()
        return if (iterator.hasNext())
            iterator.next().getChange()
        else
            0.toDouble()
    }

    override fun to(): CoinsDataModel {
        return CoinsDataModel(id, name, symbol, rank, price(), change24h())
    }
}

