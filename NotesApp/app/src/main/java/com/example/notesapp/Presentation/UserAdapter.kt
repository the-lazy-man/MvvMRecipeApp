package com.example.notesapp.Presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.model.User

class UserAdapter : ListAdapter<User, UserAdapter.UserViewHolder>(UsersComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val firstNameItemView: TextView = itemView.findViewById(R.id.firstNameTextView)
        private val lastNameItemView: TextView = itemView.findViewById(R.id.lastNameTextView)
        private val ageItemView: TextView = itemView.findViewById(R.id.ageTextView)

        fun bind(user: User?) {
            firstNameItemView.text = user?.firstName
            lastNameItemView.text = user?.lastName
            ageItemView.text = user?.age.toString()
        }

        companion object {
            fun create(parent: ViewGroup): UserViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_layout, parent, false)
                return UserViewHolder(view)
            }
        }
    }

    class UsersComparator : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }
    }
}