package com.example.notes.loftcoinkotlin.data

import android.util.Log
import com.example.notes.loftcoinkotlin.core.data.CoinsRepository
import com.example.notes.loftcoinkotlin.core.data.Query
import com.example.notes.loftcoinkotlin.core.data.SortBy
import com.example.notes.loftcoinkotlin.core.util.RxSchedulers
import com.example.notes.loftcoinkotlin.data.currency.Currency
import com.example.notes.loftcoinkotlin.data.database.CacheCoin
import com.example.notes.loftcoinkotlin.data.database.LoftDatabase
import com.example.notes.loftcoinkotlin.data.net.CoinsApi
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.Callable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinsRepositoryImpl
@Inject constructor(
    private val coinsApi: CoinsApi,
    private val database: LoftDatabase,
    private val schedulers: RxSchedulers
) : CoinsRepository {


    override fun fetchListingsDatabase(query: Query): Observable<List<CoinsDataModel>> {
        return Observable.fromCallable {
            query.forceUpdate!! || database.getDao().coinsCount() == 0
        }.switchMap {
            if (it) coinsApi.fetchListings(query.currency!!) else Observable.empty()
        }.map { listing ->
            val coinsDataModel = listing.getData().map { it.to() }
            mapToCacheCoin(query, coinsDataModel)
        }.doOnNext { coins ->
            database.getDao().insertAll(coins)
        }.switchMap {
            fetchFromDb(query)
        }.switchIfEmpty {
            fetchFromDb(query)
        }.map {
            it.map { cacheCoin -> cacheCoin.to() }
        }.subscribeOn(schedulers.io())
    }

    override fun coin(id: Long, currency: Currency): Single<CoinsDataModel> {
    return fetchListingsDatabase(Query.currency(currency.getCode()).forceUpdate(false).build())
        .switchMapSingle {
            database.getDao().fetchOne(id).map {
                it.to()
            }
        }
        .firstOrError()
    }

    private fun fetchFromDb(query: Query): Observable<List<CacheCoin>> {
        return if (query.sortBy == SortBy.RANK) {
            database.getDao().fetchAllSortByRank()
        } else {
            database.getDao().fetchAllSortByPrice()
        }
    }

    private fun mapToCacheCoin(query: Query, coins: List<CoinsDataModel>): List<CacheCoin> {
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
                    query.currency!!
                )
            )
        }
        return cacheCoin
    }
}