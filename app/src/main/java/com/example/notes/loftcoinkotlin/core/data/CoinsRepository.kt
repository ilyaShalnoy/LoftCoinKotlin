package com.example.notes.loftcoinkotlin.core.data

import androidx.lifecycle.LiveData
import com.example.notes.loftcoinkotlin.data.CoinsDataModel
import com.example.notes.loftcoinkotlin.data.currency.Currency
import com.example.notes.loftcoinkotlin.data.net.NetworkCoin
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface CoinsRepository {

    fun fetchListingsDatabase(query: Query): Observable<List<CoinsDataModel>>

    fun coin(id: Long, currency: Currency): Single<CoinsDataModel>
}

data class Query(
    val currency: String?,
    val forceUpdate: Boolean?,
    val sortBy: SortBy?,
) {
    companion object Builder {
        private var currency: String? = null
        private var forceUpdate: Boolean? = null
        private var sortBy: SortBy? = null

        fun currency(value: String) = apply { currency = value }
        fun forceUpdate(value: Boolean) = apply { forceUpdate = value }
        fun sortBy(value: SortBy) = apply { sortBy = value }

        fun build(): Query {
            return Query(
                currency,
                forceUpdate,
                sortBy
            )
        }
    }

}