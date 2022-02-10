package com.example.notes.loftcoinkotlin.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.notes.loftcoinkotlin.R
import com.example.notes.loftcoinkotlin.databinding.ActivityMainBinding
import com.example.notes.loftcoinkotlin.databinding.ActivityWelcomeBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
        NavigationUI.setupWithNavController(binding.toolbar,
            navController, AppBarConfiguration
                .Builder(binding.bottomNavigation.menu)
                .build()
        )
    }

}