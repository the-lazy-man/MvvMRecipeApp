package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var clickbtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clickbtn = findViewById(R.id.ClickMeBtn)
        clickbtn.setOnClickListener{
            val MyIntent = Intent(this, SplashScreenActivity::class.java)
            startActivity(MyIntent)
        }


    }
}