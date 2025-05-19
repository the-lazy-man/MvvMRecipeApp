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

    @GET("lookup.php")
    fun getMealDetails(@Query("i") id : String ) : Call<MealList>

    @GET("filter.php")
    fun getlistPopularItems(@Query("c") categoryName : String ) : Call<MealsByPopularCategoryList>

    @GET("categories.php")
    fun getCategoryList() : Call<CategoryList>

    @GET("filter.php")
    fun getMealsByCategory(@Query("c") categoryName : String ) : Call<MealsInACategoryList>

    @GET("search.php")
    fun searchMeal(@Query("s") searchQuery : String ) : Call<MealList>
}


/* Retrofit automatically appends ? and & when you use @Query.
    @GET("lookup.php?")
    fun getMealDetails(@Query("i") id : String): Call<MealList>
        Would produce something like:
        -> lookup.php??i=1234

    If you add more queries later:
    @GET("lookup.php")
    fun getMealDetails(
        @Query("i") id: String,
        @Query("lang") lang: String
    ): Call<MealList>
    Retrofit will build:

    -> lookup.php?i=1234&lang=en
*/