package com.example.notes.loftcoinkotlin.data.net

import com.example.notes.loftcoinkotlin.data.CoinsRepository
import java.io.IOException

class CloudCoinsRepository(private val coinsApi: CoinsApi) : CoinsRepository {

    override fun fetchListings(currency: String): List<Coin> {
        val response = coinsApi.fetchListings(currency).execute()
        if (response.isSuccessful) {
            response.body()?.let { listings ->
                return@fetchListings listings.to()
            }
        } else {
            response.errorBody()?.let {
                throw IOException(it.string())
            }
        }
        return emptyList()
    }
}