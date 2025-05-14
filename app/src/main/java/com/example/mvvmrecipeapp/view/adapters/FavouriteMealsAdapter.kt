package com.example.mvvmrecipeapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmrecipeapp.Model.dataClasses.Meal
import com.example.mvvmrecipeapp.databinding.MealItemBinding

class FavouriteMealsAdapter : RecyclerView.Adapter<FavouriteMealsAdapter.FavouriteMealsAdapterViewHolder>() {
    inner class FavouriteMealsAdapterViewHolder(val binding: MealItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavouriteMealsAdapterViewHolder {
        //Use the context of the RecyclerView to inflate the item_product.xml layout, create a View from it,
        // and return it without attaching it immediately to the RecyclerView.
        return FavouriteMealsAdapterViewHolder(
            MealItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: FavouriteMealsAdapterViewHolder, position: Int) {
        val meal = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this)
                .load(meal.strMealThumb)
                .into(holder.binding.imgMeal)
            holder.binding.tvMealName.text = meal.strMeal
        }
    }

}


//package com.example.mvvmrecipeapp.adapters
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.mvvmrecipeapp.Model.Meal
//import com.example.mvvmrecipeapp.databinding.MealItemBinding
//
//class FavouriteMealsAdapter : RecyclerView.Adapter<FavouriteMealsAdapter.FavouriteMealsAdapterViewHolder>() {
//
//    private var meals: ArrayList<Meal> = ArrayList<Meal>()
//
//    fun setMeals(newMeals: ArrayList<Meal>) {
//        this.meals = newMeals
//        this.notifyDataSetChanged()
//    }
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteMealsAdapterViewHolder {
//        return FavouriteMealsAdapterViewHolder(
//            MealItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        )
//    }
//    override fun getItemCount(): Int = meals.size
//
//    override fun onBindViewHolder(holder: FavouriteMealsAdapterViewHolder, position: Int) {
//        val meal = meals[position]
//        Log.d("meal from FavouriteMealsAdapter", "${meal.strMeal}")
//        Glide.with(holder.itemView)
//            .load(meal.strMealThumb)
//            .into(holder.binding.imgMeal)
//
//        holder.binding.tvMealName.text = meal.strMeal
//    }
//
//    inner class FavouriteMealsAdapterViewHolder(val binding: MealItemBinding) : RecyclerView.ViewHolder(binding.root)
//}


