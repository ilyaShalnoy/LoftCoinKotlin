package com.example.notes.loftcoinkotlin.core.util

interface Formatter<T> {
    fun format(value: T): String
}