package com.example.mvvmrecipeapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmrecipeapp.Model.dataClasses.PopularMeal
import com.example.mvvmrecipeapp.databinding.PopularItemsBinding

class MostPopularMealAdapter() : RecyclerView.Adapter<MostPopularMealAdapter.PopularMealViewHolder>() {
    lateinit var onItemClick : ( (PopularMeal) -> Unit)
    private var mealsList = ArrayList<PopularMeal>()

    fun setMeals(mealslist : ArrayList<PopularMeal>) {
        this.mealsList = mealslist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {
        return PopularMealViewHolder(
            PopularItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
       return mealsList.size
    }

    override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealsList[position].strMealThumb)
            .into(holder.binding.imgPopularMeal)
            holder.itemView.setOnClickListener {
                onItemClick(mealsList[position])
            }
    }

    class PopularMealViewHolder(val binding : PopularItemsBinding) : RecyclerView.ViewHolder(binding.root)

}
/*
        the above return statement is just converting the xml file popular_meals into kotlin views/ code which is the inflate part
        and giving it to the PopularMealViewHolder class. The class holds references to the views inside of the xml file.
        Because we don't need to use findViewbyId() method inside the class as we used viewbinding on the xml file.  otherwise,
        the class would have been like this:

           override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {
               val meal = mealsList[position]
               // Example: Assuming there's an ImageView with id 'imgMeal' and a TextView with id 'tvMealName' in your layout
               holder.imgMeal.setImageResource(meal.imageResId)
               // Replace with your image loading logic
               holder.tvMealName.text = meal.name
           }

           class PopularMealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
               val imgMeal: ImageView = itemView.findViewById(R.id.imgMeal)
               val tvMealName: TextView = itemView.findViewById(R.id.tvMealName)
           }
        */