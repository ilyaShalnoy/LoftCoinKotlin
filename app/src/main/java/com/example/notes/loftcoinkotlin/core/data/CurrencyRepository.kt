package com.example.notes.loftcoinkotlin.core.data

import androidx.lifecycle.LiveData
import com.example.notes.loftcoinkotlin.data.currency.Currency
import io.reactivex.rxjava3.core.Observable

interface CurrencyRepository {

    fun availableCurrencies(): LiveData<List<Currency>>

    fun currency(): Observable<Currency>

    fun updateCurrency(currency: Currency)

}