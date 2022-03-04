package com.example.notes.loftcoinkotlin.ui.rates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.notes.loftcoinkotlin.core.data.CoinsRepository
import com.example.notes.loftcoinkotlin.core.data.Query
import com.example.notes.loftcoinkotlin.core.data.CurrencyRepository
import com.example.notes.loftcoinkotlin.core.data.SortBy
import com.example.notes.loftcoinkotlin.data.CoinsDataModel
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class RatesViewModel @Inject constructor(private val coinsRepository: CoinsRepository, private val currencyRepository: CurrencyRepository) : ViewModel() {

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing get() = _isRefreshing

    private val forceRefresh = MutableLiveData(AtomicBoolean(true))

    private val sortBy = MutableLiveData(SortBy.RANK)

    private var _coinsLiveData: LiveData<List<CoinsDataModel>>
    val coinsLiveData get() = _coinsLiveData

    private var sortingIndex = 1

    init {

        val query = Transformations.switchMap(forceRefresh) { refresh ->
            return@switchMap Transformations.switchMap(currencyRepository.currency()) { currency ->
                refresh.set(true)
                isRefreshing.postValue(true)
                Transformations.map(sortBy) { sort ->
                    return@map Query(
                        currency = currency.getCode(),
                        forceUpdate = refresh.getAndSet(false),
                        sortBy = sort
                    )
                }
            }
        }

        val coins = Transformations.switchMap(query) {
            coinsRepository.fetchListingsDatabase(it)
        }

        _coinsLiveData = Transformations.map(coins) {
            isRefreshing.postValue(false)
            return@map it
        }
    }

    fun refresh() {
        forceRefresh.postValue(AtomicBoolean(true))
    }

    fun switchSortingOrder() {
        sortBy.postValue(SortBy.values()[sortingIndex++ % SortBy.values().size])
    }


}