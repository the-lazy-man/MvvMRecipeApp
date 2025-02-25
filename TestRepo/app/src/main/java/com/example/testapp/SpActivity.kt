package com.example.testapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class   SpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sp)

        val usernameTextView = findViewById<TextView>(R.id.usernameTextView)
        val passwordTextView = findViewById<TextView>(R.id.passwordTextView)

        // Retrieve data from SharedPreferences
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "No Username Found")
        val password = sharedPreferences.getString("password", "No Password Found")

        // Display the data
        usernameTextView.text = "Username: $username"
        passwordTextView.text = "Password: $password"
    }
}
