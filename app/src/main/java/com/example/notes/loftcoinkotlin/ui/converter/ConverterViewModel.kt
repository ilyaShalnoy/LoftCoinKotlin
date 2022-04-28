package com.example.notes.loftcoinkotlin.ui.converter

import androidx.lifecycle.ViewModel
import com.example.notes.loftcoinkotlin.core.data.CoinsRepository
import com.example.notes.loftcoinkotlin.core.data.CurrencyRepository
import com.example.notes.loftcoinkotlin.core.util.RxSchedulers
import com.example.notes.loftcoinkotlin.data.CoinsDataModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ConverterViewModel @Inject constructor(
    private val coinsRepository: CoinsRepository,
    private val currencyRepository: CurrencyRepository,
    private val rxSchedulers: RxSchedulers
) : ViewModel() {

    fun topCoins(): Observable<List<CoinsDataModel>> {
        return currencyRepository.currency()
            .switchMap { currency ->
                coinsRepository.topCoins(currency)
            }.replay(1)
            .autoConnect()
            .observeOn(rxSchedulers.main())
    }
}