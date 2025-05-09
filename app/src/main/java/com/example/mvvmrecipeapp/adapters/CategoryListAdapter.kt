package com.example.mvvmrecipeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmrecipeapp.Model.Category
import com.example.mvvmrecipeapp.databinding.CategoryItemBinding

class CategoryListAdapter: RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder>() {
    private var CategoryList = ArrayList<Category>()
    lateinit var onCategoryItemClick : ( (Category) -> Unit)
    fun setCategoryList(categorylist : ArrayList<Category>) {
        this.CategoryList = categorylist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun getItemCount(): Int {
        return CategoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(CategoryList[position].strCategoryThumb)
            .into(holder.binding.imgCategory)
        holder.binding.tvCategoryName.text = CategoryList[position].strCategory
        holder.itemView.setOnClickListener {
            onCategoryItemClick(CategoryList[position])
        }
    }

    class CategoryViewHolder(val binding : CategoryItemBinding) : RecyclerView.ViewHolder(binding.root)

}