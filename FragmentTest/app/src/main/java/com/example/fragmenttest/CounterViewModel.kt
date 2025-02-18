package com.example.fragmenttest
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    // LiveData to store count
    private val _count = MutableLiveData<Int>(4)
    val count: LiveData<Int> get() = _count

    // Function to increment the count
    fun increment() {
        _count.value = (_count.value ?: 0) + 1
    }
}
