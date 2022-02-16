package com.example.notes.loftcoinkotlin.core.util

import java.text.NumberFormat

class PriceFormatter : Formatter<Double> {
    override fun format(value: Double): String = NumberFormat.getCurrencyInstance().format(value)
}
