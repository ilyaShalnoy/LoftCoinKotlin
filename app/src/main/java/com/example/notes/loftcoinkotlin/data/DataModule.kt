package com.example.notes.loftcoinkotlin.data

import com.example.notes.loftcoinkotlin.core.data.CoinsRepository
import com.example.notes.loftcoinkotlin.core.data.CurrencyRepository
import com.example.notes.loftcoinkotlin.data.currency.CurrencyRepositoryImpl
import com.example.notes.loftcoinkotlin.data.database.DatabaseModule
import com.example.notes.loftcoinkotlin.data.net.NetworkModule
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class DataModule {

    @Binds
    abstract fun bindCoinsRepo(coinsRepoImpl: CoinsRepositoryImpl): CoinsRepository

    @Binds
    abstract fun bindCurrencyRepo(currencyRepo: CurrencyRepositoryImpl): CurrencyRepository

}
