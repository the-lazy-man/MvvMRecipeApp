package com.example.testapptwo

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context : Context, workerParams: WorkerParameters)  : Worker(context, workerParams) {
    override fun doWork(): Result {
        // Simulate a long-running task
        Log.d("MyWorker", "Work is being done...")
        Thread.sleep(4000) // Simulate a network operation
        Log.d("MyWorker", "Work is done!")
        return Result.success() // Indicate success
    }
}