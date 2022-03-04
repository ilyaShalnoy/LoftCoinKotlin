package com.example.notes.loftcoinkotlin.data

data class CoinsDataModel(
    private val id: Int,
    private val name: String,
    private val symbol: String,
    private val rank: Int,
    private val price: Double,
    private val change24: Double
) {
    fun getId() = id
    fun getName() = name
    fun getSymbol() = symbol
    fun getRank() = rank
    fun getPrice() = price
    fun getChange() = change24
}