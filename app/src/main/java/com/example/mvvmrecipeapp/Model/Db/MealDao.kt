package com.example.mvvmrecipeapp.Model.Db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmrecipeapp.Model.dataClasses.Meal

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertMeal(meal: Meal)
    @Delete
    suspend fun deleteMeal(meal: Meal)
    @Query("SELECT * FROM mealInformation ORDER BY idMeal ASC")
    fun getAllMeals(): LiveData<List<Meal>>

    @Query("SELECT MAX(sort) FROM mealInformation")
    suspend fun getMaxSort(): Int?

}