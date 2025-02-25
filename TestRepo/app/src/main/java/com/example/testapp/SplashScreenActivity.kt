package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            // Intent to start the main activity
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
            finish() // Finish the splash screen activity
        }, 2000) // Delay for 2 seconds (adjust as needed)
    }
}