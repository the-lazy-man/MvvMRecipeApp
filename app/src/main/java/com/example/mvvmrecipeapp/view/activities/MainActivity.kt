package com.example.mvvmrecipeapp.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.mvvmrecipeapp.Model.Db.MealsDatabase
import com.example.mvvmrecipeapp.R
import com.example.mvvmrecipeapp.domain.viewmodels.HomeViewModelFactory
import com.example.mvvmrecipeapp.domain.viewmodels.HomeViewmodel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    val viewModel : HomeViewmodel by lazy {
        val mealDatabase = MealsDatabase.getInstance(this)
        val viewModelFactory = HomeViewModelFactory(mealDatabase)
        ViewModelProvider(this,viewModelFactory)[HomeViewmodel::class.java]

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.btm_nav)
        val navController = Navigation.findNavController(this, R.id.host_fragment)
        NavigationUI.setupWithNavController(bottomNavigation,navController)
    }
}