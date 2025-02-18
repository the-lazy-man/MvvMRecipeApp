package com.example.fragmenttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    private lateinit var navController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostfragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            navController =  navHostfragment.navController
        setupActionBarWithNavController(navController)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction().add(R.id.fragment_container, firstFragment())
//        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || onSupportNavigateUp()
    }
}