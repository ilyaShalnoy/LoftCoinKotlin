package com.example.notes.loftcoinkotlin.data.currency

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notes.loftcoinkotlin.R
import com.example.notes.loftcoinkotlin.core.data.CurrencyRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

const val KEY_CURRENCY = "currency"
const val PREFERENCE_CURRENCY_FILE = "list currency"

@Singleton
class CurrencyRepositoryImpl @Inject constructor(context: Context) : CurrencyRepository {

    private val availableCurrencies: HashMap<String, Currency> = HashMap()

    private var sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFERENCE_CURRENCY_FILE, Context.MODE_PRIVATE)

    init {
        availableCurrencies["USD"] = Currency("$", "USD", context.getString(R.string.usd))
        availableCurrencies["EUR"] = Currency("E", "EUR", context.getString(R.string.eur))
        availableCurrencies["RUB"] = Currency("R", "RUB", context.getString(R.string.rub))
    }


    override fun availableCurrencies(): LiveData<List<Currency>> {
        val currencyLiveData = MutableLiveData<List<Currency>>()
        currencyLiveData.value = ArrayList(availableCurrencies.values)
        return currencyLiveData
    }

    override fun currency(): Observable<Currency> {
        return Observable.create { emitter ->
            val listener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
                if (!emitter.isDisposed) {
                    availableCurrencies[sharedPreferences.getString(key, "USD")]
                }
            }
            sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
            emitter.setCancellable {
                sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
            }
            emitter.onNext(availableCurrencies[sharedPreferences.getString(KEY_CURRENCY, "USD")])
        }
    }

    override fun updateCurrency(currency: Currency) {
        sharedPreferences.edit()?.putString(KEY_CURRENCY, currency.getCode())?.apply()
    }

}