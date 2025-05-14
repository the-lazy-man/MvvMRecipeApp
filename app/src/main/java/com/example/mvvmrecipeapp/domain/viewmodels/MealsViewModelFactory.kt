package com.example.mvvmrecipeapp.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmrecipeapp.Model.Db.MealsDatabase

class MealsViewModelFactory(private val mealDatabase : MealsDatabase) : ViewModelProvider.Factory {
        override fun <T : ViewModel>create(modelClass : Class<T>) : T {
            return MealDetailViewModel(mealDatabase) as T
        }

}