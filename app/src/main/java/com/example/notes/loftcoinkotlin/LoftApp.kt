package com.example.notes.loftcoinkotlin

import android.app.Application
import android.os.StrictMode
import com.example.notes.loftcoinkotlin.core.util.DebugTree
import timber.log.Timber

class LoftApp : Application() {

    private lateinit var component: BaseComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
            .application(this)
            .build()

        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults()
            Timber.plant(DebugTree());
        }

    }

    fun getComponent(): BaseComponent {
        return component
    }

}