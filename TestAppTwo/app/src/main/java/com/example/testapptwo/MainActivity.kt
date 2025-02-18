package com.example.testapptwo


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Create a WorkRequest
        val workRequest = OneTimeWorkRequest.Builder(MyWorker::class.java).build()

        // Enqueue the WorkRequest
        WorkManager.getInstance(this).enqueue(workRequest)

    }

}
