package com.example.sharedprefdemoapp

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

    fun registerUser(email: String, password: String): Boolean {
        if (sharedPreferences.contains(email)) {
            return false // User already exists
        }
        with(sharedPreferences.edit()) {
            putString(email, password)
            apply()
        }
        return true
    }

    fun loginUser(email: String, password: String): Boolean {
        val storedPassword = sharedPreferences.getString(email, null)
        return storedPassword == password
    }

    fun logoutUser() {
        with(sharedPreferences.edit()) {
            clear()
            apply()
        }
    }
}
