package com.example.fragmenttest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class DestinationFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            val view = inflater.inflate(R.layout.fragment_destination, container, false)
            val button_src = view.findViewById<Button>(R.id.button_des)
            button_src.setOnClickListener {
                findNavController().navigate(R.id.action_destinationFragment_to_homeFragment)
            }
            return view
        }
}
