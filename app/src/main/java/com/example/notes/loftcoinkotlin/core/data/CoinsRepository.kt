package com.example.notes.loftcoinkotlin.core.data

import androidx.lifecycle.LiveData
import com.example.notes.loftcoinkotlin.data.net.Coin

interface CoinsRepository {
    fun fetchListings(currency: String): List<Coin>

    fun fetchListingsDatabase(query: Query): LiveData<List<Coin>>
}

data class Query(
    val currency: String,
    val forceUpdate: Boolean = true
)