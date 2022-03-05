package com.example.notes.loftcoinkotlin.core.util

import android.content.Context
import androidx.core.os.ConfigurationCompat
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.HashMap

@Singleton
class PriceFormatter @Inject constructor() : Formatter<Double> {

    private val LOCALES = HashMap<String, Locale>()

    init {
        LOCALES["RUB"] = Locale("ru", "RU")
        LOCALES["EUR"] = Locale.GERMANY
        LOCALES["USD"] = Locale.US
    }

    fun format(currency: String, value: Double): String {
        val locale = LOCALES[currency]
        return NumberFormat.getCurrencyInstance(locale).format(value)
    }

    override fun format(value: Double): String = NumberFormat.getCurrencyInstance().format(value)
}
