package com.example.mvvmrecipeapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvmrecipeapp.R
import com.example.mvvmrecipeapp.adapters.CategoryMealsAdapter
import com.example.mvvmrecipeapp.databinding.ActivityCategoryMealsBinding
import com.example.mvvmrecipeapp.domain.CategoryMealsViewModel

class CategoryMealsActivity : AppCompatActivity() {
    private lateinit var viewModel : CategoryMealsViewModel
    private lateinit var categoryMealsAdapter : CategoryMealsAdapter
    private lateinit var binding : ActivityCategoryMealsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        categoryMealsAdapter = CategoryMealsAdapter()
        setUpRecylerView()
        viewModel = ViewModelProvider(this)[CategoryMealsViewModel::class.java]
        viewModel.getMealsByCategory(intent.getStringExtra(homeFragment.CATEGORY_NAME)!!)
        viewModel.categoriesLiveData.observe(this, { mealsList ->
            categoryMealsAdapter.setMealList(mealsList)
        })
    }

    private fun setUpRecylerView() {
        binding.rvMeals.apply {
            adapter = categoryMealsAdapter
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        }
    }
}