package com.example.notes.loftcoinkotlin.data.wallets

import com.example.notes.loftcoinkotlin.data.CoinsDataModel

data class Wallet(
    private val uid: String,
    private val coin: CoinsDataModel,
    private val balance: Double,
) {

    fun getUid(): String = uid
    fun getCoin(): CoinsDataModel = coin
    fun getBalance(): Double = balance

    companion object {
        fun create(id: String, coin: CoinsDataModel, balance: Double): Wallet {
            return Wallet(id, coin, balance)
        }
    }
}