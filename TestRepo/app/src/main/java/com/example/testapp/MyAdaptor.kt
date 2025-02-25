package com.example.testapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdaptor(private val itemList: List<DataItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // ViewHolder to hold views for each item
    class TittleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.itemtitle)

    }
    class SubTittleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val subtitleTextView: TextView = itemView.findViewById(R.id.item_subtitle)
    }
    override fun getItemViewType(position: Int): Int {
        return itemList[position].type
    }
    // Create ViewHolder instances
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  return when (viewType) {
            DataItem.TYPE_TITLE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tittle_layout, parent, false)
                TittleViewHolder(view)
            }
            DataItem.TYPE_SUBTITLE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_subtittle_layout, parent, false)
                SubTittleViewHolder(view)
            }
            else -> {
                // Handle unexpected view type
                throw IllegalArgumentException("Unknown view type: $viewType")
            }
        }

    }

    // Bind data to the ViewHolder
    override fun onBindViewHolder(holder:  RecyclerView.ViewHolder, position: Int) {
        val item = itemList[position]
        if(holder is TittleViewHolder)
            holder.titleTextView.text = item.title.toString()
        else if(holder is SubTittleViewHolder){
            holder.subtitleTextView.text = item.subtitle.toString()
        }
    }

    // Return the total number of items
    override fun getItemCount(): Int {
        return itemList.size
    }
}
