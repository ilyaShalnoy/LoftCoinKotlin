package com.example.notes.loftcoinkotlin.data

import com.example.notes.loftcoinkotlin.data.net.Coin

interface CoinsRepository {
    fun fetchListings(currency: String): List<Coin>
}