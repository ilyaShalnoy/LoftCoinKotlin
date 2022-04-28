package com.example.notes.loftcoinkotlin.core

import android.content.Context
import com.example.notes.loftcoinkotlin.core.data.CoinsRepository
import com.example.notes.loftcoinkotlin.core.data.CurrencyRepository
import com.example.notes.loftcoinkotlin.core.data.WalletsRepository
import com.example.notes.loftcoinkotlin.core.util.ImageLoader
import com.example.notes.loftcoinkotlin.core.util.Notifier
import com.example.notes.loftcoinkotlin.core.util.RxSchedulers

interface BaseComponent {
    fun context(): Context

    fun coinsRepository(): CoinsRepository

    fun currencyRepository(): CurrencyRepository

    fun walletsRepository(): WalletsRepository

    fun imageLoader(): ImageLoader

    fun schedulers(): RxSchedulers

    fun notifier(): Notifier
}