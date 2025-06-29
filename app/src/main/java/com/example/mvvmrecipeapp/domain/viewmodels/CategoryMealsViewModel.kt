package com.example.mvvmrecipeapp.domain.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmrecipeapp.Model.dataClasses.MealOfACategory
import com.example.mvvmrecipeapp.Model.dataClasses.MealsInACategoryList
import com.example.mvvmrecipeapp.Model.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryMealsViewModel: ViewModel() {
    private var _categoriesLiveData = MutableLiveData<List<MealOfACategory>>()
    val categoriesLiveData: LiveData<List<MealOfACategory>> get() = _categoriesLiveData
    fun getMealsByCategory(categoryName : String){
        RetrofitInstance.api.getMealsByCategory(categoryName).enqueue(object : Callback<MealsInACategoryList>{
            override fun onResponse(
                call: Call<MealsInACategoryList>,
                response: Response<MealsInACategoryList>
            ) {
               response.body()?.let{
                   _categoriesLiveData.postValue(it.meals)
               }
            }

            override fun onFailure(call: Call<MealsInACategoryList>, t: Throwable) {
                Log.d("Request error -> ", "error = ${t.message.toString()}")
            }
        })
    }
}