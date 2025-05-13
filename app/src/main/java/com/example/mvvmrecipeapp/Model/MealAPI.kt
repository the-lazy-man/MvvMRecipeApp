package com.example.mvvmrecipeapp.Model
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