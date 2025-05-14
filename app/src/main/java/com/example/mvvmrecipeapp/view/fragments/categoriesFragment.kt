package com.example.mvvmrecipeapp.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvmrecipeapp.view.adapters.CategoryListAdapter
import com.example.mvvmrecipeapp.databinding.FragmentCategoriesBinding
import com.example.mvvmrecipeapp.domain.viewmodels.HomeViewmodel
import com.example.mvvmrecipeapp.view.activities.CategoryMealsActivity
import com.example.mvvmrecipeapp.view.activities.MainActivity


class categoriesFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var viewmodel: HomeViewmodel
    private lateinit var  categoryListAdapter: CategoryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewmodel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoriesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()
        observeCategories()
        onCategoryClick()
    }

    private fun onCategoryClick() {
        categoryListAdapter.onCategoryItemClick = {category ->
            val intent = Intent(activity, CategoryMealsActivity::class.java)
            intent.putExtra(homeFragment.CATEGORY_NAME,category.strCategory)
            startActivity(intent)
        }
    }

    private fun observeCategories() {
        viewmodel.categoriesLiveData.observe(viewLifecycleOwner, Observer {categories->
            categoryListAdapter.setCategoryList(categories as ArrayList)
        })
    }

    private fun prepareRecyclerView() {
        categoryListAdapter = CategoryListAdapter()
        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(activity,3,GridLayoutManager.VERTICAL,false)
            adapter = categoryListAdapter
        }
    }
}