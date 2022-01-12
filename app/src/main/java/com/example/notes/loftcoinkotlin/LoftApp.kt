package com.example.notes.loftcoinkotlin

import android.app.Application
import android.os.StrictMode

class LoftApp: Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG) {
            StrictMode.enableDefaults()
        }


    }

}