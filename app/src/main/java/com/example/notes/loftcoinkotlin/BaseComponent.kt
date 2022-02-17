package com.example.notes.loftcoinkotlin

import android.content.Context
import com.example.notes.loftcoinkotlin.data.CoinsRepository
import com.example.notes.loftcoinkotlin.data.CurrencyRepository

interface BaseComponent {
    fun context(): Context

    fun coinsRepository(): CoinsRepository

    fun currencyRepository(): CurrencyRepository
}