package com.example.notes.loftcoinkotlin.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.notes.loftcoinkotlin.core.data.CoinsRepository
import com.example.notes.loftcoinkotlin.core.data.Query
import com.example.notes.loftcoinkotlin.core.data.SortBy
import com.example.notes.loftcoinkotlin.data.database.CacheCoin
import com.example.notes.loftcoinkotlin.data.database.LoftDatabase
import com.example.notes.loftcoinkotlin.data.net.CoinsApi
import timber.log.Timber
import java.io.IOException
import java.util.concurrent.ExecutorService
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList

@Singleton
class CoinsRepositoryImpl
@Inject constructor(
    private val coinsApi: CoinsApi,
    private val database: LoftDatabase,
    private val executor: ExecutorService
) : CoinsRepository {


    override fun fetchListingsDatabase(query: Query): LiveData<List<CoinsDataModel>> {

        fetchFromNetworkIfNecessary(query)
        return fetchFromDb(query)
    }

    private fun fetchFromDb(query: Query): LiveData<List<CoinsDataModel>> {
        val coins = if (query.sortBy == SortBy.RANK) {
            database.getDao().fetchAllSortByRank()
        } else {
            database.getDao().fetchAllSortByPrice()
        }
        return Transformations.map(coins) {
            it.map { cacheCoin ->
                cacheCoin.to()
            }
        }
    }

    private fun fetchFromNetworkIfNecessary(query: Query) =
        executor.submit {
            if (query.forceUpdate || database.getDao().coinsCount() == 0) {
                try {
                    val response = coinsApi.fetchListings(query.currency).execute()
                    if (response.isSuccessful) {
                        val listings = response.body()
                        if (listings != null) {
                            val coins = listings.getData().map {
                                it.to()
                            }
                            saveCoinsIntoDB(coins, query)
                        }
                    } else {
                        throw IOException(response.errorBody()?.string())
                    }
                } catch (e: IOException) {
                    Timber.e(e)
                }
            }
        }


    private fun saveCoinsIntoDB(coins: List<CoinsDataModel>, query: Query) {
        val cacheCoin = ArrayList<CacheCoin>()

        for (coin in coins) {
            cacheCoin.add(
                CacheCoin(
                    coin.getId(),
                    coin.getName(),
                    coin.getSymbol(),
                    coin.getRank(),
                    coin.getPrice(),
                    coin.getChange(),
                    query.currency
                )
            )
        }
        database.getDao().insertAll(cacheCoin)
    }

}