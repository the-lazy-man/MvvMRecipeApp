package com.example.mvvmrecipeapp.view.fragments

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.mvvmrecipeapp.Model.dataClasses.Meal

import com.bumptech.glide.request.target.Target
import com.bumptech.glide.load.DataSource
import com.example.mvvmrecipeapp.Model.dataClasses.Category
import com.example.mvvmrecipeapp.Model.dataClasses.PopularMeal
import com.example.mvvmrecipeapp.view.adapters.CategoryListAdapter
import com.example.mvvmrecipeapp.view.adapters.MostPopularMealAdapter
import com.example.mvvmrecipeapp.databinding.FragmentHomeBinding
import com.example.mvvmrecipeapp.domain.viewmodels.HomeViewmodel
import com.example.mvvmrecipeapp.view.activities.CategoryMealsActivity
import com.example.mvvmrecipeapp.view.activities.MainActivity
import com.example.mvvmrecipeapp.view.activities.MealDetailsActivity
import com.example.mvvmrecipeapp.view.fragments.bottomSheet.BottomSheet

class homeFragment : Fragment() {
    private lateinit var popularMealsAdapter: MostPopularMealAdapter
    private lateinit var categoryListAdapter: CategoryListAdapter
    private lateinit var binding : FragmentHomeBinding
    private lateinit var homeViewModel : HomeViewmodel
    private lateinit var randomMeal : Meal

    companion object{
        const val MEAL_ID="com.example.mvvmrecipeapp.view.fragments.idMeal"
        const val MEAL_NAME="com.example.mvvmrecipeapp.view.fragments.nameMeal"
        const val MEAL_THUMB="com.example.mvvmrecipeapp.view.fragments.thumbMeal"
        const val CATEGORY_NAME=" com.example.mvvmrecipeapp.view.fragments.categoryName"
        const val MEAL_STR=" com.example.mvvmrecipeapp.view.fragments.strMeal"
        const val MEAL_AREA=" com.example.mvvmrecipeapp.view.fragments.strArea"


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        homeViewModel = ViewModelProvider(this).get(HomeViewmodel::class.java)
        homeViewModel = (activity as MainActivity).viewModel
        popularMealsAdapter = MostPopularMealAdapter()
        categoryListAdapter = CategoryListAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container,false)
        return binding.root

    }
    override  fun onViewCreated(view : View , savedInstanceState : Bundle?){
        super.onViewCreated(view , savedInstanceState)
        homeViewModel.getRandomMeal()
        observeRandomMeal()
        binding.randomMeal.setOnClickListener {
            onRandomMealClick()
        }

        homeViewModel.getPopularItems()
        setUpPopularMealsRecylerView()
        observePopularItemsLiveData()
        onPopularItemClick()
        setUpCategoryListRecylerView()
        homeViewModel.getCategoryList()
        observeCategoryListLiveData()
        onCategoryListItemClick()

        onPopularItemLongClick()

    }

    private fun onPopularItemLongClick() {
        popularMealsAdapter.onLongItemClick = {meal->
            val bottomSheetFragment = BottomSheet.newInstance(meal.idMeal)
            bottomSheetFragment.show(childFragmentManager, "Meal Info")
        }
    }

    private fun onCategoryListItemClick() {
        categoryListAdapter.onCategoryItemClick = { categoryItem ->
            val intent = Intent(activity, CategoryMealsActivity::class.java)
            intent.apply {
               putExtra(CATEGORY_NAME,categoryItem.strCategory)
            }
            startActivity(intent)

        }
    }

    private fun observeCategoryListLiveData() {
        homeViewModel.categoriesLiveData.observe(viewLifecycleOwner, Observer {
            categoryListAdapter.setCategoryList(it as ArrayList<Category>)
        })
    }

    private fun setUpCategoryListRecylerView() {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(activity, 3,GridLayoutManager.VERTICAL, false)
            adapter = categoryListAdapter
        }
    }

    private fun onPopularItemClick() {
        popularMealsAdapter.onItemClick = {meal->
            val intent = Intent(activity, MealDetailsActivity::class.java)
            intent.putExtra(MEAL_ID, meal.idMeal)
            intent.putExtra(MEAL_NAME, meal.strMeal)
            intent.putExtra(MEAL_THUMB, meal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun setUpPopularMealsRecylerView() {
        binding.recViewMealsPopular.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL, false)
        binding.recViewMealsPopular.adapter = popularMealsAdapter
    }

    private fun observePopularItemsLiveData() {
        homeViewModel.popularItemsLiveData.observe(viewLifecycleOwner, object : Observer<List<PopularMeal>>{
            override fun onChanged(popularMealList: List<PopularMeal>) {
                popularMealsAdapter.setMeals(popularMealList as ArrayList<PopularMeal>)
            }
        })
    }

    private fun onRandomMealClick(){
            val intent = Intent(activity, MealDetailsActivity::class.java)
            intent.putExtra(MEAL_ID , randomMeal.idMeal)
            intent.putExtra(MEAL_NAME , randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB , randomMeal.strMealThumb)
            startActivity(intent)
    }
//    fun observeRandomMeal(){
//        homeViewModel.randomMealLiveData.observe(viewLifecycleOwner,
//            { meal ->
//                binding.imgRandomMeal.visibility = View.VISIBLE
//                binding.progressBar.visibility = View.INVISIBLE
//                Glide.with(this@homeFragment).load(meal!!.strMealThumb).into(binding.imgRandomMeal)
//                this.randomMeal = meal
//            }
//        )
//    }
    fun observeRandomMeal() {
        homeViewModel.randomMealLiveData.observe(viewLifecycleOwner) { meal ->
        binding.progressBar.visibility = View.VISIBLE
        binding.imgRandomMeal.visibility = View.INVISIBLE

        Glide.with(this@homeFragment)
            .load(meal!!.strMealThumb)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean
                ): Boolean {
                    // Hide progress even on failure
                    binding.progressBar.visibility = View.GONE
                    return false // allow Glide to handle error placeholder if any
                }

                override fun onResourceReady(
                    resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean
                ): Boolean {
                    binding.progressBar.visibility = View.GONE
                    binding.imgRandomMeal.visibility = View.VISIBLE
                    return false
                }
            })
            .into(binding.imgRandomMeal)
        this.randomMeal = meal
    }
}

}
