package com.example.notes.loftcoinkotlin

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Singleton
    @Provides
    fun context(app: Application): Context = app.applicationContext

}