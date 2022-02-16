package com.example.notes.loftcoinkotlin.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.notes.loftcoinkotlin.R
import java.security.AccessControlContext

class CurrencyRepositoryImpl(private val context: Context) : CurrencyRepository {


    override fun availableCurrencies(): LiveData<List<Currency>> {
        return AllCurrenciesLiveData(context)
    }

    override fun currency(): LiveData<Currency> {
        TODO("Not yet implemented")
    }

    override fun updateCurrency(currency: Currency) {
        TODO("Not yet implemented")
    }

    class AllCurrenciesLiveData(private val context: Context) : LiveData<List<Currency>>() {

        override fun onActive() {
            val currencies = ArrayList<Currency>()
            currencies.add(Currency("$", "USD", context.getString(R.string.usd)))
            currencies.add(Currency("E", "EUR", context.getString(R.string.eur)))
            currencies.add(Currency("R", "RUB", context.getString(R.string.rub)))
            value = currencies
        }

    }

}