package com.example.testapptwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class TestActivity : AppCompatActivity() {
    lateinit var txtCoroutine : TextView
    lateinit var buttonClickMe : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        txtCoroutine = findViewById<TextView>(R.id.textView)
        buttonClickMe = findViewById(R.id.button)
        buttonClickMe.setOnClickListener{
            CoroutineScope(Dispatchers.Main).launch {
//                txtCoroutine.text = "changed from IO"
                ChangeCoroutineText()
            }
        }

    }
    suspend fun ChangeCoroutineText() {
//        withContext(Dispatchers.IO) { // Switch to IO Thread
            txtCoroutine.text = "changed from IO"
//            val text = "changed from IO"
//            withContext(Dispatchers.Main) { // Switch back to Main thread for UI update
//                txtCoroutine.text = text
//            }
//        }
    }
}
