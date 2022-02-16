package com.example.notes.loftcoinkotlin.core.util

import java.util.*

class ChangeFormatter : Formatter<Double> {
    override fun format(value: Double): String {
       return String.format(Locale.US, "%.2f%%", value)
    }
}