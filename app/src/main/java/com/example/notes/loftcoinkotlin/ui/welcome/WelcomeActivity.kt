package com.example.notes.loftcoinkotlin.ui.welcome

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.loftcoinkotlin.R
import com.example.notes.loftcoinkotlin.databinding.ActivityWelcomeBinding
import com.example.notes.loftcoinkotlin.ui.main.MainActivity
import com.example.notes.loftcoinkotlin.ui.splash.SPLASH_ACTIVITY_PREFERENCES
import com.example.notes.loftcoinkotlin.ui.widget.LinePagerIndicatorDecoration


const val KEY_SHOW_WELCOME = "KEY_SHOW_WELCOME"

class WelcomeActivity : AppCompatActivity() {

    private lateinit var preferences: SharedPreferences
    private lateinit var snapHelper: PagerSnapHelper
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializationRecyclerView()

        preferences = getSharedPreferences(SPLASH_ACTIVITY_PREFERENCES, Context.MODE_PRIVATE)

        binding.btnStart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            preferences.edit()
                .putBoolean(KEY_SHOW_WELCOME, false)
                .apply()
            finish()
        }
    }

    private fun initializationRecyclerView() {
        with(binding.recycler) {
            adapter = WelcomeAdapter()
            layoutManager = LinearLayoutManager(this@WelcomeActivity, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
            addItemDecoration(LinePagerIndicatorDecoration(this@WelcomeActivity))
        }

        snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.recycler)
    }

    override fun onDestroy() {
        snapHelper.attachToRecyclerView(null)
        binding.recycler.adapter = null
        super.onDestroy()
    }
}