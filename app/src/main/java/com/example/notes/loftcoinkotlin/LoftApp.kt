package com.example.notes.loftcoinkotlin

import android.app.Application
import android.content.Context
import android.os.StrictMode
import com.example.notes.loftcoinkotlin.core.BaseComponent
import com.example.notes.loftcoinkotlin.core.DaggerAppComponent
import com.example.notes.loftcoinkotlin.core.util.DebugTree
import com.google.firebase.messaging.FirebaseMessaging
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

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Timber.e(task.exception, "Fetching FCM registration token failed")
                return@addOnCompleteListener
            }
            Timber.d("fcm: %s", task.result)
        }
    }
}

val Context.baseComponent: BaseComponent
    get() = when (this) {
        is LoftApp -> baseComponent
        else -> this.applicationContext.baseComponent
    }
