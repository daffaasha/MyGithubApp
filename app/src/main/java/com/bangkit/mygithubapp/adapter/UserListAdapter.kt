package com.bangkit.mygithubapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.core.domain.model.User
import com.bangkit.mygithubapp.databinding.UserItemBinding
import com.bangkit.mygithubapp.util.loadImage

class UserListAdapter(
    private val listUser: List<User>,
    private val onClick: (User) -> Unit
) : RecyclerView.Adapter<UserListAdapter.HomeViewHolder>() {

    class HomeViewHolder(var binding : UserItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount() = listUser.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val avatar = listUser[position].avatar
        val username = listUser[position].username

        with(holder.binding) {
            tvUsername.text = username
            ivAvatar.loadImage(avatar)
        }

        holder.itemView.setOnClickListener {
            onClick(listUser[position])
        }
    }
}