package com.example.mvvmrecipeapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvmrecipeapp.Model.Meal
import com.example.mvvmrecipeapp.R
import com.example.mvvmrecipeapp.adapters.FavouriteMealsAdapter
import com.example.mvvmrecipeapp.databinding.FragmentFavouritesBinding
import com.example.mvvmrecipeapp.domain.HomeViewmodel


class favouritesFragment : Fragment() {
    lateinit var favouriteViewModel : HomeViewmodel
    lateinit var binding : FragmentFavouritesBinding
    private lateinit var favouriteMealsAdapter : FavouriteMealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favouriteViewModel = (activity as MainActivity).viewModel
        favouriteMealsAdapter = FavouriteMealsAdapter()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavouritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerView()
        observeFavourites()
    }

    private fun prepareRecyclerView() {
        binding.rvFavourites.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter = favouriteMealsAdapter
        }
    }
    private fun observeFavourites(){
        favouriteViewModel.favouriteMealsLiveData.observe(viewLifecycleOwner, Observer {meal ->
            favouriteMealsAdapter.differ.submitList(meal as ArrayList<Meal>)
//            Log.d("mealList", "${favouriteViewModel.favouriteMealsLiveData.value}")
        })
    }

}