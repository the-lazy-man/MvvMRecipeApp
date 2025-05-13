package com.example.mvvmrecipeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmrecipeapp.Model.Category
import com.example.mvvmrecipeapp.Model.MealOfACategory
import com.example.mvvmrecipeapp.Model.MealsInACategoryList
import com.example.mvvmrecipeapp.databinding.MealItemBinding

class CategoryMealsAdapter : RecyclerView.Adapter<CategoryMealsAdapter.CategoryMealsViewHolder>(){
    inner class CategoryMealsViewHolder(val binding : MealItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    private var mealList = ArrayList<MealOfACategory>()
    fun setMealList(mealsList : List<MealOfACategory>){
        this.mealList = mealsList as ArrayList<MealOfACategory>
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMealsViewHolder {
        return CategoryMealsViewHolder(MealItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    override fun onBindViewHolder(holder: CategoryMealsViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealList[position].strMealThumb)
            .into(holder.binding.imgMeal)
        holder.binding.tvMealName.text = mealList[position].strMeal
    }
}