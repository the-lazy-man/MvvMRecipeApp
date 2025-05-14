package com.example.mvvmrecipeapp.Model.retrofit
import com.example.mvvmrecipeapp.Model.dataClasses.CategoryList
import com.example.mvvmrecipeapp.Model.dataClasses.MealList
import com.example.mvvmrecipeapp.Model.dataClasses.MealsByPopularCategoryList
import com.example.mvvmrecipeapp.Model.dataClasses.MealsInACategoryList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealAPI {
    @GET("random.php")
    fun getRandomMeal() : Call<MealList>

    @GET("lookup.php?")
    fun getMealDetails(@Query("i") id : String ) : Call<MealList>

    @GET("filter.php?")
    fun getlistPopularItems(@Query("c") categoryName : String ) : Call<MealsByPopularCategoryList>

    @GET("categories.php")
    fun getCategoryList() : Call<CategoryList>

    @GET("filter.php?")
    fun getMealsByCategory(@Query("c") categoryName : String ) : Call<MealsInACategoryList>


}