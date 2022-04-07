package com.example.notes.loftcoinkotlin.data

import java.util.*

data class Transaction(
    private val uid: String,
    private val coin: CoinsDataModel,
    private val amount: Double,
    private val createAt: Date
) {
    companion object {
        fun create(uid: String, coin: CoinsDataModel, amount: Double, createAt: Date): Transaction =
            Transaction(uid, coin, amount, createAt)
    }

    fun getUid(): String = uid
    fun getCoin(): CoinsDataModel = coin
    fun getAmount(): Double = amount
    fun getCreateAt(): Date = createAt
}
