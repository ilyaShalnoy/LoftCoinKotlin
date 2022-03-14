package com.example.notes.loftcoinkotlin.core.util

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.ExecutorService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RxSchedulersImpl @Inject constructor(executor: ExecutorService) : RxSchedulers {

    private var ioSchedulers: Scheduler = Schedulers.from(executor)

    override fun io(): Scheduler {
        return ioSchedulers
    }

    override fun cmp(): Scheduler {
        return Schedulers.computation()
    }

    override fun main(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}