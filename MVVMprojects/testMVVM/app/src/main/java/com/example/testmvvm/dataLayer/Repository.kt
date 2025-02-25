package com.example.testmvvm.dataLayer

class Repository(private val userInstance : UserDao , private val  apiServiceInstance : ApiService) {

    fun GetAllUsers()  = userInstance.getAllUsers()

    suspend fun insertUserRepoFun(userArgument : User){
            userInstance.insertUser(userArgument)
    }
    suspend fun getUserFromAPI(){
        val users = apiServiceInstance.getUsers()
        users.forEach{
            insertUserRepoFun(it)
        }
    }
}
