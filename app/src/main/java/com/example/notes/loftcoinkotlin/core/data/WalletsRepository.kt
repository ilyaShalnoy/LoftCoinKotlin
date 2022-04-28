package com.example.notes.loftcoinkotlin.core.data

import com.example.notes.loftcoinkotlin.data.Transaction
import com.example.notes.loftcoinkotlin.data.wallets.Wallet
import com.example.notes.loftcoinkotlin.data.currency.Currency
import io.reactivex.rxjava3.core.Observable

interface WalletsRepository {

    fun wallet(currency: Currency): Observable<List<Wallet>>

    fun transaction(wallet: Wallet): Observable<List<Transaction>>

    fun addWallet(currency: Currency, takenIds: List<Int>)
}