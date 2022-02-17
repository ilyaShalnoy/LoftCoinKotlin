package com.example.notes.loftcoinkotlin.ui.main

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.notes.loftcoinkotlin.LoftApp
import com.example.notes.loftcoinkotlin.R
import com.example.notes.loftcoinkotlin.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var component: MainComponent

    @Inject
    lateinit var fragmentFactory: FragmentFactory

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        val baseComponent = (newBase!!.applicationContext as LoftApp).getComponent()
        component = DaggerMainComponent.builder().baseComponent(baseComponent).build()
        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.fragmentFactory = fragmentFactory
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
        NavigationUI.setupWithNavController(
            binding.toolbar,
            navController, AppBarConfiguration
                .Builder(binding.bottomNavigation.menu)
                .build()
        )
    }

}