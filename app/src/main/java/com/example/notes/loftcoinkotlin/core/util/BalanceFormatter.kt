package com.example.notes.loftcoinkotlin.core.util

import com.example.notes.loftcoinkotlin.data.wallets.Wallet
import java.text.DecimalFormat
import java.text.NumberFormat
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BalanceFormatter @Inject constructor() : Formatter<Wallet> {

    override fun format(value: Wallet): String {
        val format = NumberFormat.getCurrencyInstance() as DecimalFormat
        val symbol = format.decimalFormatSymbols
        symbol.currencySymbol = value.getCoin().getSymbol()
        format.decimalFormatSymbols = symbol
        return format.format(value.getBalance())
    }
}