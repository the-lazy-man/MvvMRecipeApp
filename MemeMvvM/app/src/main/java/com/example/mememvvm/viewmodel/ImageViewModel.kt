package com.example.mememvvm.viewmodel
import androidx.lifecycle.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mememvvm.model.ImageData
import com.example.mememvvm.model.ImageRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ImageViewModel : ViewModel(){
    private val _images = MutableLiveData<List<ImageData.Image>>()
    val images: LiveData<List<ImageData.Image>> get() = _images

    fun loadImages() {
        ImageRepository().getImages().enqueue(object : Callback<ImageData> {
            override fun onResponse(call: Call<ImageData>, response: Response<ImageData>) {
                if(response.isSuccessful) {
                    _images.value = response.body()?.data?.images
                    Log.d("Response received","${response.body()}")
                }
                else {
                    Log.d("SeverErrorLog", "${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<ImageData>, t: Throwable) {
                Log.d("NetworkRequestFailure","${t.message}")
            }
        })
    }

}

/*
A more optimised viewmodel for a more optimised data class

class ImageViewModel : ViewModel() {
    private val _images = MutableLiveData<List<ImageData.Image>>()
    val images: LiveData<List<ImageData.Image>> get() = _images

    fun loadImages() {
        ImageRepository().getImages().enqueue(object : Callback<ImageData> {
            override fun onResponse(call: Call<ImageData>, response: Response<ImageData>) {
                if (response.isSuccessful) {
                    response.body()?.let { imageData ->
                        _images.postValue(imageData.images) // Directly assign images
                        Log.d("Response received", imageData.toString())
                    } ?: Log.d("ResponseError", "Response body is null")
                } else {
                    Log.d("ServerError", "${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<ImageData>, t: Throwable) {
                Log.d("NetworkFailure", "${t.message}")
            }
        })
    }
}*/