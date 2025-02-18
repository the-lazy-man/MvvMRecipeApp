package com.example.mememvvm.model

import retrofit2.Call

// ImageRepository.kt
class ImageRepository {
    private val apiService = RetrofitInstance.Instance

    fun getImages(): Call<ImageData> {
        return apiService.getImageData()
    }
}