package com.example.notes.loftcoinkotlin.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.notes.loftcoinkotlin.core.data.CoinsRepository
import com.example.notes.loftcoinkotlin.core.data.Query
import com.example.notes.loftcoinkotlin.data.database.CacheCoin
import com.example.notes.loftcoinkotlin.data.database.LoftDatabase
import com.example.notes.loftcoinkotlin.data.net.NetworkCoin
import com.example.notes.loftcoinkotlin.data.net.CoinsApi
import timber.log.Timber
import java.io.IOException
import java.util.*
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

    override fun fetchListings(currency: String): List<CoinsDataModel> {
        val response = coinsApi.fetchListings(currency).execute()
        if (response.isSuccessful) {
            response.body()?.let { listings ->
                return@fetchListings listings.getData().map {
                    it.to()
                }
            }
        } else {
            throw IOException(response.errorBody()?.string())
        }
        return emptyList()
    }

    override fun fetchListingsDatabase(query: Query): LiveData<List<CoinsDataModel>> {
        val refresh = MutableLiveData<Boolean>()
        executor.submit {
            refresh.postValue(query.forceUpdate || database.getDao().coinsCount() == 0)
        }
        return Transformations.switchMap(refresh) { r ->
            if (r)
                return@switchMap fetchFromNetwork(query)
            else
               return@switchMap fetchFromDb(query)
        }
    }

    private fun fetchFromDb(query: Query): LiveData<List<CoinsDataModel>> {
        return Transformations.map(database.getDao().fetchAll()) {
            it.map { cacheCoin ->
                cacheCoin.to()
            }
        }
    }

    private fun fetchFromNetwork(query: Query): LiveData<List<CoinsDataModel>> {
        val liveData = MutableLiveData<List<CoinsDataModel>>()
        executor.submit {
            try {
                val response = coinsApi.fetchListings(query.currency).execute()
                if (response.isSuccessful) {
                    val listings = response.body()
                    if (listings != null) {
                        val coins = listings.getData().map {
                            it.to()
                        }
                        saveCoinsIntoDB(coins)
                        liveData.postValue(coins)
                    }

                } else {
                    throw IOException(response.errorBody()?.string())
                }
            } catch (e: IOException) {
                Timber.e(e)
            }

        }
        return liveData
    }

    private fun saveCoinsIntoDB(coins: List<CoinsDataModel>) {
        val cacheCoin = ArrayList<CacheCoin>()

        for (coin in coins) {
            cacheCoin.add(
                CacheCoin(
                    coin.getId(),
                    coin.getName(),
                    coin.getSymbol(),
                    coin.getRank(),
                    coin.getPrice(),
                    coin.getChange()
                )
            )
        }
        Log.d("saveCoinsIntoDB", cacheCoin.toString())
        database.getDao().insertAll(cacheCoin)
    }

}