package com.example.mvvmrecipeapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmrecipeapp.Model.dataClasses.Meal
import com.example.mvvmrecipeapp.view.adapters.MealsAdapter
import com.example.mvvmrecipeapp.databinding.FragmentFavouritesBinding
import com.example.mvvmrecipeapp.domain.viewmodels.HomeViewmodel
import com.example.mvvmrecipeapp.view.activities.MainActivity
import com.google.android.material.snackbar.Snackbar


class favouritesFragment : Fragment() {
    lateinit var favouriteViewModel : HomeViewmodel
    lateinit var binding : FragmentFavouritesBinding
    private lateinit var mealsAdapter : MealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favouriteViewModel = (activity as MainActivity).viewModel
        mealsAdapter = MealsAdapter()

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

        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val meal = mealsAdapter.differ.currentList[position]
                favouriteViewModel.deleteMeal(meal)
                Snackbar.make(requireView(),"Meal deleted",Snackbar.LENGTH_LONG).setAction(
                    "Undo",
                    // clicking on undo is adding the item at the end of the list. Need to Fix it.
                    View.OnClickListener {
                        favouriteViewModel.insertMeal(meal)
                    }
                ).show()

            }
        }
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.rvFavourites)

    }

    private fun prepareRecyclerView() {
        binding.rvFavourites.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter = mealsAdapter
        }
    }
    private fun observeFavourites(){
        favouriteViewModel.favouriteMealsLiveData.observe(viewLifecycleOwner, Observer {meal ->
            mealsAdapter.differ.submitList(meal as ArrayList<Meal>)
//            Log.d("mealList", "${favouriteViewModel.favouriteMealsLiveData.value}")
        })
    }

}