package com.example.notes.loftcoinkotlin.core.data

import androidx.lifecycle.LiveData
import com.example.notes.loftcoinkotlin.data.CoinsDataModel
import com.example.notes.loftcoinkotlin.data.net.NetworkCoin

interface CoinsRepository {

    fun fetchListingsDatabase(query: Query): LiveData<List<CoinsDataModel>>
}

data class Query(
    val currency: String,
    val forceUpdate: Boolean = true,
    val sortBy: SortBy
)