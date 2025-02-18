package com.example.mememvvm.model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("3/album/lDRB2") // Only the endpoint part
    fun getImageData(@Query("client_id") clientId: String = "0fe6d34a74fece0"): Call<ImageData>
}

object RetrofitInstance {
    private const val BASE_URL = "https://api.imgur.com/" // Base URL with trailing slash
    val Instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}