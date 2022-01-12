package com.example.notes.loftcoinkotlin.ui.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.notes.loftcoinkotlin.R
import com.example.notes.loftcoinkotlin.ui.main.MainActivity
import com.example.notes.loftcoinkotlin.ui.welcome.KEY_SHOW_WELCOME
import com.example.notes.loftcoinkotlin.ui.welcome.WelcomeActivity

const val SPLASH_ACTIVITY_PREFERENCES = "SPLASH_ACTIVITY_PREFERENCES"

class SplashActivity : AppCompatActivity() {

    private lateinit var preferences: SharedPreferences

    private val handler = Handler(Looper.getMainLooper())
    private lateinit var goNext: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        preferences = getSharedPreferences(SPLASH_ACTIVITY_PREFERENCES, Context.MODE_PRIVATE)

        goNext = if (preferences.getBoolean(KEY_SHOW_WELCOME, true)) {
            Runnable {
                startActivity(Intent(this, WelcomeActivity::class.java))
            }
        } else {
            Runnable {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
        handler.postDelayed(goNext, 2000)
    }

    override fun onStop() {
        handler.removeCallbacks(goNext)
        super.onStop()
    }
}