package com.example.notes.loftcoinkotlin.core.util

import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChangeFormatter @Inject constructor(): Formatter<Double> {

    override fun format(value: Double): String {
       return String.format(Locale.US, "%.2f%%", value)
    }
}