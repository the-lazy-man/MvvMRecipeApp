package com.example.testmvvm.domain

import androidx.lifecycle.ViewModel

class UserViewModel(private val getUsersUseCase: GetUsersUseCase) : ViewModel() {
    var vmUserList = getUsersUseCase.userList
    val users = getUsersUseCase.execute()
}
