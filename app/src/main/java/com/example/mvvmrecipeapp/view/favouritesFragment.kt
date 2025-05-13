package com.example.mvvmrecipeapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvmrecipeapp.R
import com.example.mvvmrecipeapp.databinding.FragmentFavouritesBinding
import com.example.mvvmrecipeapp.domain.HomeViewmodel
import com.example.mvvmrecipeapp.adapters.FavouriteMealsAdapter


class favouritesFragment : Fragment() {
    lateinit var favouriteViewModel : HomeViewmodel
    lateinit var binding : FragmentFavouritesBinding
    private lateinit var favouriteMealsAdapter : FavouriteMealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentFavouritesBinding.inflate(layoutInflater)
        favouriteViewModel = (activity as MainActivity).viewModel
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerView()
        observeFavourites()
    }

    private fun prepareRecyclerView() {
        favouriteMealsAdapter = FavouriteMealsAdapter()
        binding.rvFavourites.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter = favouriteMealsAdapter
        }
    }

    private fun observeFavourites(){
        favouriteViewModel.favouriteMealsLiveData.observe(viewLifecycleOwner, Observer {meal ->
            favouriteMealsAdapter.setMeals(meal)
//            favouriteMealsAdapter.differ.submitList(meal.toList())
//            Log.d("mealList", "${favouriteViewModel.favouriteMealsLiveData.value}")
        })
    }

}