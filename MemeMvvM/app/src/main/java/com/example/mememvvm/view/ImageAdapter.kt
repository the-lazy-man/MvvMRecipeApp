package com.example.mememvvm.view

// ImageAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mememvvm.R
import com.example.mememvvm.model.ImageData

class ImageAdapter(private val images: List<ImageData.Image>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val nameView: TextView = itemView.findViewById(R.id.nameView)
        val idView: TextView = itemView.findViewById(R.id.idView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]
        holder.nameView.text = image.name
        holder.idView.text = image.Id

        val requestOption = RequestOptions()
        requestOption.placeholder(R.drawable.ic_launcher_background)

        Glide.with(holder.itemView.context)
            .setDefaultRequestOptions(requestOption)
            .load(image.link)
            .into(holder.imageView)
    }

    override fun getItemCount() = images.size
}