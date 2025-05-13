package com.example.mvvmrecipeapp.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.mvvmrecipeapp.databinding.ActivityMealDetailsBinding
import com.example.mvvmrecipeapp.domain.MealDetailViewModel
import com.example.mvvmrecipeapp.view.homeFragment.Companion.MEAL_ID
import com.example.mvvmrecipeapp.view.homeFragment.Companion.MEAL_NAME
import com.example.mvvmrecipeapp.view.homeFragment.Companion.MEAL_STR
import com.example.mvvmrecipeapp.view.homeFragment.Companion.MEAL_THUMB
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmrecipeapp.Model.Db.MealsDatabase
import com.example.mvvmrecipeapp.Model.Meal
import com.example.mvvmrecipeapp.domain.MealsViewModelFactory

class MealDetailsActivity : AppCompatActivity() {
    private lateinit var mealId : String
    private lateinit var mealName : String
    private lateinit var mealThumb : String
    private lateinit var mealYoutubeLink: String
    private lateinit var binding : ActivityMealDetailsBinding
    private lateinit var mealDetailsViewModel : MealDetailViewModel
    private var mealToSave : Meal? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mealDatabase = MealsDatabase.getInstance(this)
        val viewModelFactory = MealsViewModelFactory(mealDatabase)
        mealDetailsViewModel = ViewModelProvider(this, viewModelFactory)[MealDetailViewModel::class.java]
//        mealDetailsViewModel = ViewModelProvider(this)[MealDetailViewModel::class.java]

        // for youtube icon to not be overlapped by device's bottom navigation buttons
        ViewCompat.setOnApplyWindowInsetsListener(binding.youtubeBar) { view, insets ->
            val bottomInset = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            view.setPadding(0, 0, 0, bottomInset + 16)
            insets
        }
        loadingCase()
        setUpViewWithMealInfo()
        observeMealDetails()
        OnYouTubeImageClick()
        onFavoriteClick()
    }

    private fun onFavoriteClick() {
        binding.btnSaveToFav.setOnClickListener{
            mealToSave?.let { mealDetailsViewModel.insertMeal(it) }
            Toast.makeText(this,"Meal Saved to Favourites",Toast.LENGTH_SHORT).show()
        }
    }

    private fun OnYouTubeImageClick() {
        binding.imgYoutube.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mealYoutubeLink))
            startActivity(intent)

        }
    }
    private fun observeMealDetails() {
        mealDetailsViewModel.getMealDetails(mealId)
        mealDetailsViewModel.mealDetailsLiveData.observe(this) { meal ->
            OnResponseCase()
            mealToSave = meal
            binding.apply {
                tvCategoryInfo.text = "Category : ${meal.strCategory}"
                tvAreaInfo.text = "Area : ${meal.strArea}"
                tvContent.text = meal.strInstructions
                mealYoutubeLink = meal.strYoutube.toString()
            }
        }
    }

    fun getMealInfoFromIntent() {
        val tempIntent = intent
        this.mealId = tempIntent.getStringExtra(MEAL_ID)!!
        this.mealName = tempIntent.getStringExtra(MEAL_NAME)!!
        this.mealThumb = tempIntent.getStringExtra(MEAL_THUMB)!!
    }

    fun setUpViewWithMealInfo() {
        getMealInfoFromIntent()
        binding.apply {
            collapsingToolbar.title = mealName
            Glide.with(applicationContext)
                .load(mealThumb)
                .into(imgMeal)
        }
    }
    fun loadingCase() {
        binding.progressBar.visibility = View.VISIBLE
        binding.tvInstructions.visibility = View.INVISIBLE
        binding.tvAreaInfo.visibility = View.INVISIBLE
        binding.tvCategoryInfo.visibility = View.INVISIBLE
        binding.btnSaveToFav.visibility = View.INVISIBLE
        binding.imgYoutube.visibility = View.INVISIBLE

    }
    fun OnResponseCase() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.tvInstructions.visibility = View.VISIBLE
        binding.tvAreaInfo.visibility = View.VISIBLE
        binding.tvCategoryInfo.visibility = View.VISIBLE
        binding.btnSaveToFav.visibility = View.VISIBLE
        binding.imgYoutube.visibility = View.VISIBLE
    }

}