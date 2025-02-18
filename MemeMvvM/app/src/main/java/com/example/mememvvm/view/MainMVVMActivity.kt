package com.example.mememvvm.view
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mememvvm.R
import com.example.mememvvm.viewmodel.ImageViewModel

class MainMVVMActivity  : AppCompatActivity() {

    private lateinit var viewModel: ImageViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ImageAdapter(emptyList())
        recyclerView.adapter = adapter

        // Initialize ViewModel with Factory

        viewModel = ViewModelProvider(this).get(ImageViewModel::class.java)


        val pb = findViewById(R.id.progressBar) as ProgressBar

        pb.visibility = View.VISIBLE
//        viewModel.loadImages()
        // Observe the images LiveData
        viewModel.images.observe(this) { images ->
            adapter = ImageAdapter(images)
            recyclerView.adapter = adapter
            recyclerView.visibility = View.VISIBLE
            pb.visibility = View.GONE
        }

        // Load imaes

    }

    override fun onPause() {
        super.onPause()

        viewModel.loadImages()
    }
}
