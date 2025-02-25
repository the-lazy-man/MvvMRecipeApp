package com.example.notesapp.Presentation
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.Domain.UserViewModel
import com.example.notesapp.model.User
import com.example.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = UserAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        userViewModel.allUsers.observe(this) { users ->
            users?.let { adapter.submitList(it) }
        }

        binding.addButton.setOnClickListener {
            val firstName = binding.firstNameEditText.text.toString()
            val lastName = binding.lastNameEditText.text.toString()
            val age = binding.ageEditText.text.toString().toIntOrNull() ?: 0

            if (firstName.isNotEmpty() && lastName.isNotEmpty() && age > 0) {
                val user = User(firstName = firstName, lastName = lastName, age = age)
                userViewModel.insert(user)
                binding.firstNameEditText.text.clear()
                binding.lastNameEditText.text.clear()
                binding.ageEditText.text.clear()
            }
        }
    }
}