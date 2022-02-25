package com.example.notes.loftcoinkotlin

import android.app.Application
import android.content.Context
import android.os.StrictMode
import com.example.notes.loftcoinkotlin.core.BaseComponent
import com.example.notes.loftcoinkotlin.core.DaggerAppComponent
import com.example.notes.loftcoinkotlin.core.util.DebugTree
import timber.log.Timber

class LoftApp : Application() {

    lateinit var baseComponent: BaseComponent

    override fun onCreate() {
        super.onCreate()

        baseComponent = DaggerAppComponent.builder()
            .application(this)
            .build()

        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults()
            Timber.plant(DebugTree());
        }
    }
}

val Context.baseComponent: BaseComponent
    get() = when (this) {
        is LoftApp -> baseComponent
        else -> this.applicationContext.baseComponent
    }
