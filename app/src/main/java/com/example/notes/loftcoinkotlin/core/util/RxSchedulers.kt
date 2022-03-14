package com.example.notes.loftcoinkotlin.core.util

import io.reactivex.rxjava3.core.Scheduler

interface RxSchedulers {

    fun io(): Scheduler
    fun cmp(): Scheduler
    fun main(): Scheduler

}