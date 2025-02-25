package com.example.testapp


import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class loginActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
//        val SaveButton = findViewById<Button>(R.id.saveButton)
        // get the saved password and username
        val sp = getSharedPreferences("pref", MODE_PRIVATE)
        usernameEditText.setText(sp.getString("username",""))
        passwordEditText.setText(sp.getString("password",""))

        // check a key inside the sp file

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Save to SharedPreferences
            val sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("username", username)
            editor.putString("password", password)
            editor.commit()


            // Navigate to DisplayActivity
            val intent = Intent(this, SpActivity::class.java)
            startActivity(intent)
        }
//        SaveButton.setOnClickListener {
//            val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
//            val username = sharedPreferences.getString("username", "No Username Found")
//            val password = sharedPreferences.getString("password", "No Password Found")
//
//            // Display the data
//            usernameEditText.setText("${username}")
//            passwordEditText.setText("$password")
//
//        }
}
}
