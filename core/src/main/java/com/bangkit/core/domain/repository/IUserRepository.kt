package com.bangkit.core.domain.repository

import com.bangkit.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IUserRepository {

    fun getUserByUsername(username: String): Flow<List<User>>

    fun getDetailUser(id: Int): Flow<User>

    fun getAllFavoriteUser(): Flow<List<User>>

    fun getUserFollowers(id: Int): Flow<List<User>>

    fun getUserFollowing(id: Int): Flow<List<User>>
}