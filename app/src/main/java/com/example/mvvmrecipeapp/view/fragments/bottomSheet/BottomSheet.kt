package com.example.mvvmrecipeapp.view.fragments.bottomSheet

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.mvvmrecipeapp.R
import com.example.mvvmrecipeapp.databinding.FragmentBottomSheetBinding
import com.example.mvvmrecipeapp.domain.viewmodels.HomeViewmodel
import com.example.mvvmrecipeapp.view.activities.MainActivity
import com.example.mvvmrecipeapp.view.activities.MealDetailsActivity
import com.example.mvvmrecipeapp.view.fragments.homeFragment
import com.example.mvvmrecipeapp.view.fragments.homeFragment.Companion.MEAL_ID
import com.example.mvvmrecipeapp.view.fragments.homeFragment.Companion.MEAL_NAME
import com.example.mvvmrecipeapp.view.fragments.homeFragment.Companion.MEAL_THUMB
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheet : BottomSheetDialogFragment() {
    private var mealId: String? = null
    private var mealName: String? = null
    private var mealThumb: String? = null
    private lateinit var viewmodel : HomeViewmodel
    private lateinit var binding : FragmentBottomSheetBinding
//    private lateinit var param1 : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mealId = it.getString(MEAL_ID)
        }
        viewmodel = (activity as MainActivity).viewModel

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mealId?.let { viewmodel.getMealsById(it) }
        observeBottomSheetMeal()
        onBottomSheetDialogClick()
    }

    private fun onBottomSheetDialogClick() {
        binding.bottomSheet.setOnClickListener {
            if (mealName != null && mealThumb != null) {
                val intent = Intent(activity, MealDetailsActivity::class.java)
                intent.putExtra(MEAL_ID, mealId)
                intent.putExtra(MEAL_NAME, mealName)
                intent.putExtra(MEAL_THUMB, mealThumb)

                startActivity(intent)
                    dismiss()
            }
        }
    }
        fun observeBottomSheetMeal() {
            viewmodel.bottomSheetMealsLiveData.observe(viewLifecycleOwner, Observer { meal ->
                binding.tvBottomSheetMealName.text = meal.strMeal
                binding.tvBottomSheetArea.text = meal.strArea
                binding.tvBottomSheetCategory.text = meal.strCategory
                Glide.with(this).load(meal.strMealThumb).into(binding.imgBottomSheet)

                mealName = meal.strMeal
                mealThumb = meal.strMealThumb
            })
        }

        companion object {
            @JvmStatic
            fun newInstance(param1: String) = BottomSheet().apply {
                    arguments = Bundle().apply {
                        putString(MEAL_ID, param1)
                    }
                }
        }

}
