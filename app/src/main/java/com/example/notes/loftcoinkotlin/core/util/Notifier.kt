package com.example.notes.loftcoinkotlin.core.util

import io.reactivex.rxjava3.core.Completable

interface Notifier {

    fun sendMessage(title: String, message: String, receiver: Class<*>): Completable

}