package com.example.notes.loftcoinkotlin.data

import androidx.lifecycle.LiveData

interface CurrencyRepository {

    fun availableCurrencies(): LiveData<List<Currency>>

    fun currency(): LiveData<Currency>

    fun updateCurrency(currency: Currency)

}