package com.example.mememvvm.model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


data class ImageData(val status: Int, val success: Boolean, val data: Album) {
    // Nested Classes --> read it again
    data class Album(
        val images: List<Image>
    )
    data class Image(
        val Id: String,
        val name: String?,
        val link: String
    )
}
/*  A more optimised structure but we need to modify viewmodel for this as well
    so i put it here as comment.
data class ImageData(
    val success: Boolean,
    val images: List<Image>
) {
    data class Image(
        val id: String,
        val name: String?,
        val link: String
    )
}
*/