package com.example.mvvmrecipeapp.Model.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mvvmrecipeapp.Model.dataClasses.Meal

@Database(entities = [Meal::class], version = 2, exportSchema = false)
@TypeConverters(MealTypeConvertor::class)
abstract class MealsDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
    companion object {
        @Volatile
        private var INSTANCE: MealsDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE mealInformation ADD COLUMN sort INTEGER DEFAULT 0")
            }
        }

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
                ).addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}