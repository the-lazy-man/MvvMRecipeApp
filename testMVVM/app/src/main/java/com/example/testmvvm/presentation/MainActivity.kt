package com.example.testmvvm.presentation

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Update
import com.example.testmvvm.R
import com.example.testmvvm.dataLayer.MyDatabase
import com.example.testmvvm.dataLayer.Repository
import com.example.testmvvm.dataLayer.RetrofitInstance
import com.example.testmvvm.domain.GetUsersUseCase
import com.example.testmvvm.domain.UserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_view)

        val database = MyDatabase.getInstance(this)
        val ApiServiceInstance = RetrofitInstance.api
        val userRepository = Repository(database.userDao(), ApiServiceInstance)
        val getUsersUseCase = GetUsersUseCase(userRepository)

        userViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return UserViewModel(getUsersUseCase) as T
            }
        })[UserViewModel::class.java]

//        var mylistArray = userViewModel.vmUserList
        val listView: ListView = findViewById(R.id.mylistview)
        adapter = ItemAdapter(this, arrayListOf())
        listView.adapter = adapter
        userRepository.GetAllUsers().observe(this){UpdatedUserlist ->
            adapter.clear()
            adapter.addAll(UpdatedUserlist)
            adapter.notifyDataSetChanged()
        }

            // Update UI with user data

        }
    }

