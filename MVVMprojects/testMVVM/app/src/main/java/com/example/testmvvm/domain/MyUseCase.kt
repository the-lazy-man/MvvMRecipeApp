package com.example.testmvvm.domain

import com.example.testmvvm.dataLayer.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetUsersUseCase(private val repository: Repository) {
    var userList = repository.GetAllUsers()
    fun execute(){
        CoroutineScope(Dispatchers.IO).launch{
            repository.getUserFromAPI()
        }
        repository.GetAllUsers()
    }

}
