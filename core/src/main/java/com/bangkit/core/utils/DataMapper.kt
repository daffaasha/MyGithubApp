package com.bangkit.core.utils

import com.bangkit.core.data.local.entity.FavoriteEntity
import com.bangkit.core.data.remote.response.DetailResponse
import com.bangkit.core.data.remote.response.FollowResponse
import com.bangkit.core.data.remote.response.ItemsItem
import com.bangkit.core.domain.model.User

object DataMapper {
    fun listUserToDomain(input: List<ItemsItem>): List<User> {
        val listUser = ArrayList<User>()
        input.map {
            val user = User(
                username = it.login,
                avatar = it.avatarUrl
            )
            listUser.add(user)
        }
        return listUser
    }

    fun detailToDomain(input: DetailResponse): User {
        return User(
            name = input.name,
            username = input.login,
            followers = input.followers,
            following = input.following,
            avatar = input.avatarUrl
        )
    }

    fun followToDomain(input: List<FollowResponse>): List<User> {
        val listUser = ArrayList<User>()
        input.map {
            val user = User (
                username = it.login,
                avatar = it.avatarUrl
            )
            listUser.add(user)
        }
        return listUser
    }

    fun entityToDomain(input: List<FavoriteEntity>): List<User> {
        val listUser = ArrayList<User>()
        input.map {
            val user = User (
                username = it.username,
                avatar = it.avatar
            )
            listUser.add(user)
        }
        return listUser
    }
}