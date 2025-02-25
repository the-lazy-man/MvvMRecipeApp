package com.example.testmvvm.dataLayer

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers() : List<User>
}

object RetrofitInstance {
    private const val BASE_URL = "https://api.github.com/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Base URL
            .addConverterFactory(GsonConverterFactory.create()) // JSON Converter
            .build()
            .create(ApiService::class.java)
    }
}

