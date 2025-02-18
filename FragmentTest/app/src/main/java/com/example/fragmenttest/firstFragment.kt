package com.example.fragmenttest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.findViewTreeViewModelStoreOwner

class firstFragment : Fragment() {

    private val viewModel1: CounterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val counterTxt = view.findViewById<TextView>(R.id.txt1Counter)
        val buttonInc = view.findViewById<Button>(R.id.btn1Increment)
        buttonInc.setOnClickListener{
            viewModel1.increment()
        }
        viewModel1.count.observe(viewLifecycleOwner, Observer {
            counterTxt.text = it.toString()
        })
        val buttonNav = view.findViewById<Button>(R.id.btn1NavigateSecond)
        buttonNav.setOnClickListener {
            // Replace FirstFragment with SecondFragment when button is clicked
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, SecondFragment()) // Replace current fragment
                .addToBackStack(null) // Allows back navigation
                .commit()
        }
    }
}