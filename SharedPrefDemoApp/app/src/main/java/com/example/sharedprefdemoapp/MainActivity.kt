package com.example.sharedprefdemoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_welcome_page)

        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        tvWelcome.text = "Welcome to the App!"

        btnLogout.setOnClickListener {
            val sharedPrefManager = SharedPrefManager(this)
            sharedPrefManager.logoutUser()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
