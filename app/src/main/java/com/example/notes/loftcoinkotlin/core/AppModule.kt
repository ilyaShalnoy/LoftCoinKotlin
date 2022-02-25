package com.example.notes.loftcoinkotlin.core

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Singleton
    @Provides
    fun context(app: Application): Context = app.applicationContext

    fun ioExecutor(): ExecutorService {
        val poolSize = Runtime.getRuntime().availableProcessors() * 2 + 1
        return Executors.newFixedThreadPool(poolSize)
    }
}