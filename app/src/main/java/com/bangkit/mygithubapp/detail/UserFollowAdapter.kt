package com.bangkit.mygithubapp.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.core.domain.model.User
import com.bangkit.mygithubapp.databinding.FollowUserItemBinding
import com.bangkit.mygithubapp.util.loadCircleImage

class UserFollowAdapter(
    private val listFollow: List<User>
) : RecyclerView.Adapter<UserFollowAdapter.DetailViewHolder>() {

    class DetailViewHolder(var binding: FollowUserItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val binding = FollowUserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailViewHolder(binding)
    }

    override fun getItemCount() = listFollow.size

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val avatar = listFollow[position].avatar
        val username = listFollow[position].username

        with(holder.binding) {
            tvUsername.text = username
            ivAvatar.loadCircleImage(avatar)
        }
    }
}