package com.example.notes.loftcoinkotlin.data

import androidx.lifecycle.LiveData
import com.example.notes.loftcoinkotlin.core.data.CoinsRepository
import com.example.notes.loftcoinkotlin.core.data.Query
import com.example.notes.loftcoinkotlin.data.database.LoftDatabase
import com.example.notes.loftcoinkotlin.data.net.Coin
import com.example.notes.loftcoinkotlin.data.net.CoinsApi
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinsRepositoryImpl @Inject constructor(private val coinsApi: CoinsApi, private val database: LoftDatabase) : CoinsRepository {

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

    override fun fetchListingsDatabase(query: Query): LiveData<List<Coin>> {

    }


}