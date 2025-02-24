package com.example.sharedprefdemoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
class SignUpActivity  : AppCompatActivity() {
    private lateinit var sharedPrefManager: SharedPrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        sharedPrefManager = SharedPrefManager(this)

        val etEmail = findViewById<EditText>(R.id.etSignUpEmail)
        val etPassword = findViewById<EditText>(R.id.etSignUpPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (sharedPrefManager.registerUser(email, password)) {
                Toast.makeText(this, "User registered successfully!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "User already exists!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}