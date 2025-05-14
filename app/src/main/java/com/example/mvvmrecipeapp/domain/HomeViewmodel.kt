package com.example.mvvmrecipeapp.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmrecipeapp.Model.Category
import com.example.mvvmrecipeapp.Model.CategoryList
import com.example.mvvmrecipeapp.Model.Db.MealsDatabase
import com.example.mvvmrecipeapp.Model.MealsByPopularCategoryList
import com.example.mvvmrecipeapp.Model.PopularMeal

import com.example.mvvmrecipeapp.Model.Meal
import com.example.mvvmrecipeapp.Model.MealList
import com.example.mvvmrecipeapp.Model.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewmodel(private val mealDatabase : MealsDatabase) : ViewModel() {
    private var _randomMealLiveData = MutableLiveData<Meal>()
    val randomMealLiveData: LiveData<Meal> get() = _randomMealLiveData
    private var _popularItemsLiveData = MutableLiveData<List<PopularMeal>>()
    val popularItemsLiveData: LiveData<List<PopularMeal>> get() = _popularItemsLiveData
    private var _categoriesLiveData = MutableLiveData<List<Category>>()
    val categoriesLiveData: LiveData<List<Category>> get() = _categoriesLiveData

    val favouriteMealsLiveData  : LiveData<List<Meal>> = mealDatabase.mealDao().getAllMeals()
//    var favouriteMealsLiveData : LiveData<List<Meal>> = _favouriteMealsLiveData
//
    fun getRandomMeal() {
        RetrofitInstance.api.getRandomMeal().enqueue(
            object : Callback<MealList> {
                override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                    if (response.isSuccessful) {
                        val randomMeal: Meal = response.body()!!.meals[0]
                        _randomMealLiveData.value = randomMeal
                        Log.d("Meal_Id -> ", "{${randomMeal.idMeal}}")
                        Log.d("Meal_Str -> ", "{${randomMeal.strMeal}}")
                    }
                }

                override fun onFailure(call: Call<MealList>, t: Throwable) {
                    Log.d("Request error -> ", "error = ${t.message.toString()}")
                }
            })
    }
    fun getPopularItems(){
        RetrofitInstance.api.getlistPopularItems("pasta").enqueue(object : Callback<MealsByPopularCategoryList>{
            override fun onResponse(call: Call<MealsByPopularCategoryList>, response: Response<MealsByPopularCategoryList>) {
                if(response.body() != null){
                    _popularItemsLiveData.value = response.body()!!.meals
                }
            }

            override fun onFailure(call: Call<MealsByPopularCategoryList>, t: Throwable) {
                Log.d("PopularItems Error -> ",t.message.toString())
            }
        } )
    }
    fun getCategoryList() {
        RetrofitInstance.api.getCategoryList().enqueue(object : Callback<CategoryList>{
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                if(response.body() != null){
                    _categoriesLiveData.value = response.body()!!.categories
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.d("CategoryList Error -> ",t.message.toString())
            }
        } )
    }

    fun insertMeal(meal : Meal) {
        viewModelScope.launch {
            mealDatabase.mealDao().upsertMeal(meal)
        }
    }
    fun deleteMeal(meal : Meal) {
        viewModelScope.launch{
            mealDatabase.mealDao().deleteMeal(meal)
        }
    }
}