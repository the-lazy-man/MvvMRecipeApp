package com.example.testmvvm.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.lifecycle.LiveData
import com.example.testmvvm.R
import com.example.testmvvm.dataLayer.User

class ItemAdapter(context: Context, items: ArrayList<User>) : ArrayAdapter<User>(context, 0, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val item = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        val textView = view.findViewById<TextView>(R.id.tv_name)
        textView.text = item?.name
        val textView1 = view.findViewById<TextView>(R.id.tv_email)
        textView.text = item?.email
        return view
    }
}