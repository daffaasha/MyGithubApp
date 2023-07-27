package com.bangkit.core.domain.usecase

import com.bangkit.core.domain.model.User
import kotlinx.coroutines.flow.Flow
import com.bangkit.core.data.Result

interface UserUseCase {
    fun getUserByUsername(username: String): Flow<Result<List<User>>>
    fun getDetailUser(username: String): Flow<Result<User>>
    fun getAllFavoriteUser(): Flow<List<User>>
    fun getUserFollowers(username: String): Flow<Result<List<User>>>
    fun getUserFollowing(username: String): Flow<Result<List<User>>>
    suspend fun setFavorite(user: User)
    suspend fun deleteFavorite(user: User)
    fun checkFavorite(username: String): Flow<List<User>>
}