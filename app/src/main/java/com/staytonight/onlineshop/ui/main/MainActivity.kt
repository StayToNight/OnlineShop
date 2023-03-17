package com.staytonight.onlineshop.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.staytonight.onlineshop.R
import com.staytonight.onlineshop.base.binding.viewBinding
import com.staytonight.onlineshop.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {
        binding.apply {
            bottomNavigation.itemIconTintList = null
            val bottomNavigationView = bottomNavigation
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.fcv_container) as NavHostFragment
            val navController = navHostFragment.navController

            NavigationUI.setupWithNavController(bottomNavigationView, navController)
        }
    }
}