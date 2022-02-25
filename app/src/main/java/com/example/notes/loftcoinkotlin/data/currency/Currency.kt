package com.example.notes.loftcoinkotlin.data.currency

data class Currency(
    private val symbol: String,
    private val code: String,
    private val title: String
) {
    fun getSymbol() = symbol
    fun getCode() = code
    fun getTitle() = title

}
