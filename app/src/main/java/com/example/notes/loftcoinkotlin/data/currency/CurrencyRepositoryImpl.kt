package com.example.notes.loftcoinkotlin.data.currency

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notes.loftcoinkotlin.R
import com.example.notes.loftcoinkotlin.core.data.currency.CurrencyRepository
import javax.inject.Inject
import javax.inject.Singleton

const val KEY_CURRENCY = "currency"
const val PREFERENCE_CURRENCY_FILE = "list currency"

@Singleton
class CurrencyRepositoryImpl @Inject constructor(context: Context) : CurrencyRepository {

    private val availableCurrencies: HashMap<String, Currency> = HashMap()

    private var sharedPreferences: SharedPreferences? = null

    init {
        sharedPreferences = context.getSharedPreferences(PREFERENCE_CURRENCY_FILE, Context.MODE_PRIVATE)
        availableCurrencies["USD"] = Currency("$", "USD", context.getString(R.string.usd))
        availableCurrencies["EUR"] = Currency("E", "EUR", context.getString(R.string.eur))
        availableCurrencies["RUB"] = Currency("R", "RUB", context.getString(R.string.rub))
    }


    override fun availableCurrencies(): LiveData<List<Currency>> {
        val currencyLiveData: MutableLiveData<List<Currency>> = MutableLiveData()
        currencyLiveData.value = ArrayList(availableCurrencies.values)
        return currencyLiveData
    }

    override fun currency(): LiveData<Currency> {
        return CurrenciesLiveData()
    }

    override fun updateCurrency(currency: Currency) {
        sharedPreferences?.edit()?.putString(KEY_CURRENCY, currency.getCode())?.apply()
    }

    inner class CurrenciesLiveData() : LiveData<Currency>(),
        SharedPreferences.OnSharedPreferenceChangeListener {

        override fun onActive() {
            sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
            value = availableCurrencies[sharedPreferences?.getString(KEY_CURRENCY, "USD")]
        }

        override fun onInactive() {
            sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
        }

        override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
            value = availableCurrencies[sharedPreferences?.getString(key, "USD")]
        }

    }

}