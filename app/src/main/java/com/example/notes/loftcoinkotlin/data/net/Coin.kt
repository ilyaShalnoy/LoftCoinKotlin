package com.example.notes.loftcoinkotlin.data.net

import com.squareup.moshi.Json

data class Coin(
    @Json(name = "id")
    val id: Int,    //TODO: fix public field
    @Json(name = "name")
    private val name: String,
    @Json(name = "symbol")
    val symbol: String,//TODO: fix public field
    @Json(name = "cmc_rank")
    private val rank: Int
)
