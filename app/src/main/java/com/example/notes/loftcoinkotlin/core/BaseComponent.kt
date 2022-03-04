package com.example.notes.loftcoinkotlin.core

import android.content.Context
import com.example.notes.loftcoinkotlin.core.data.CoinsRepository
import com.example.notes.loftcoinkotlin.core.data.CurrencyRepository

interface BaseComponent {
    fun context(): Context

    fun coinsRepository(): CoinsRepository

    fun currencyRepository(): CurrencyRepository
}