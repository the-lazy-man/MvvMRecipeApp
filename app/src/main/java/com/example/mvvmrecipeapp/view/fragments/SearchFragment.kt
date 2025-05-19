package com.example.mvvmrecipeapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.mvvmrecipeapp.R
import com.example.mvvmrecipeapp.databinding.FragmentSearchBinding
import com.example.mvvmrecipeapp.domain.viewmodels.HomeViewmodel
import com.example.mvvmrecipeapp.view.activities.MainActivity
import com.example.mvvmrecipeapp.view.adapters.MealsAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {
    private lateinit var binding : FragmentSearchBinding
    private lateinit var viewModel : HomeViewmodel
    private lateinit var searchAdapter : MealsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecylerView()
        binding.imgSearchArrow.setOnClickListener {
            searchMeals()
        }
        observeSearchedMealsLiveData()
        var searchJob : Job? = null
        binding.searchBox.addTextChangedListener{searchQuery ->
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                delay(500)
                viewModel.searchedMeal(searchQuery.toString())
            }
        }
    }

    private fun observeSearchedMealsLiveData() {
        viewModel.searchedMeal.observe(viewLifecycleOwner){
            mealsList ->
            searchAdapter.differ.submitList(mealsList)
        }
    }

    private fun searchMeals() {
        val searchQuery = binding.searchBox.text.toString()
        if(searchQuery.isNotEmpty()){
            viewModel.searchedMeal(searchQuery)
        }
    }

    private fun prepareRecylerView() {
        searchAdapter = MealsAdapter()
        binding.rvSearchMeals.apply {
            layoutManager = GridLayoutManager(context,2, GridLayoutManager.VERTICAL,false)
            adapter = searchAdapter
        }
    }
}