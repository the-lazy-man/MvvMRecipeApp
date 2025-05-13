package com.example.mvvmrecipeapp.Model.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvvmrecipeapp.Model.Meal

@Database(entities = [Meal::class], version = 1, exportSchema = false)
@TypeConverters(MealTypeConvertor::class)
abstract class MealsDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
    companion object {
        @Volatile
        private var INSTANCE: MealsDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MealsDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MealsDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}