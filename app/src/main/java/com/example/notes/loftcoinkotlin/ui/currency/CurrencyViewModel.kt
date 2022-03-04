package com.example.notes.loftcoinkotlin.ui.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.notes.loftcoinkotlin.core.data.currency.CurrencyRepository
import com.example.notes.loftcoinkotlin.data.currency.Currency
import javax.inject.Inject

class CurrencyViewModel @Inject constructor(private val currencyRepository: CurrencyRepository) : ViewModel() {

    fun allCurrencies(): LiveData<List<Currency>> {
        return currencyRepository.availableCurrencies()
    }

    fun updateCurrencies(currency: Currency) {
        currencyRepository.updateCurrency(currency)
    }

}