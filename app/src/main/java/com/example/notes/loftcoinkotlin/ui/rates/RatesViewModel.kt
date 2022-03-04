package com.example.notes.loftcoinkotlin.ui.rates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.notes.loftcoinkotlin.core.data.CoinsRepository
import com.example.notes.loftcoinkotlin.core.data.Query
import com.example.notes.loftcoinkotlin.core.data.currency.CurrencyRepository
import com.example.notes.loftcoinkotlin.data.CoinsDataModel
import javax.inject.Inject

class RatesViewModel @Inject constructor(private val coinsRepository: CoinsRepository, private val currencyRepository: CurrencyRepository) : ViewModel() {

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing get() = _isRefreshing

    private val forceRefresh = MutableLiveData(true)

    private var _coinsLiveData: LiveData<List<CoinsDataModel>>
    val coinsLiveData get() = _coinsLiveData


    init {

        val query = Transformations.switchMap(forceRefresh) { refresh ->
            return@switchMap Transformations.map(currencyRepository.currency()) {
                isRefreshing.postValue(true)
                return@map Query(
                    currency = it.getCode(),
                    forceUpdate = refresh
                )
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
        forceRefresh.postValue(true)
    }


}