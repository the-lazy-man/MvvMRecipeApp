package com.example.mvvmrecipeapp.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmrecipeapp.Model.Db.MealsDatabase
import com.example.mvvmrecipeapp.Model.Meal
import com.example.mvvmrecipeapp.Model.MealList
import com.example.mvvmrecipeapp.Model.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealDetailViewModel(val mealDatabse : MealsDatabase) : ViewModel() {
    private val _mealDetailsLiveData = MutableLiveData<Meal>()
    val mealDetailsLiveData: LiveData<Meal> = _mealDetailsLiveData


    fun getMealDetails(mealId: String) {
        // Fetch meal details from the API using mealId
        // Update _mealDetailsLiveData with the fetched data
        RetrofitInstance.api.getMealDetails(mealId).enqueue(
            object : Callback<MealList>{
                override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                    if(response.body() != null){
                        _mealDetailsLiveData.value = response.body()!!.meals[0]
                    }
                    else return
                }

                override fun onFailure(call: Call<MealList>, t: Throwable) {
                    Log.d("Request error -> ", "error = ${t.message.toString()}")

                }

            })
    }
    fun insertMeal(meal : Meal) {
        viewModelScope.launch {
            mealDatabse.mealDao().upsertMeal(meal)
        }
    }
    fun deleteMeal(meal : Meal) {
        viewModelScope.launch{
            mealDatabse.mealDao().deleteMeal(meal)
        }
    }
}