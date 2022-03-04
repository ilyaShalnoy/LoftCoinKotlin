package com.example.notes.loftcoinkotlin.core.data

import androidx.lifecycle.LiveData
import com.example.notes.loftcoinkotlin.data.currency.Currency

interface CurrencyRepository {

    fun availableCurrencies(): LiveData<List<Currency>>

    fun currency(): LiveData<Currency>

    fun updateCurrency(currency: Currency)

}