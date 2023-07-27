package com.bangkit.core.domain.usecase

import com.bangkit.core.domain.model.User
import com.bangkit.core.domain.repository.IUserRepository

class UserInteractor(private val userRepo: IUserRepository): UserUseCase {
    override fun getUserByUsername(username: String) = userRepo.getUserByUsername(username)

    override fun getDetailUser(username: String)= userRepo.getDetailUser(username)

    override fun getAllFavoriteUser() = userRepo.getAllFavoriteUser()

    override fun getUserFollowers(username: String) = userRepo.getUserFollowers(username)

    override fun getUserFollowing(username: String) = userRepo.getUserFollowing(username)

    override fun setFavorite(user: User) = userRepo.setFavorite(user)

    override fun deleteFavorite(username: String) = userRepo.deleteFavorite(username)

    override fun checkFavorite(username: String) = userRepo.checkFavorite(username)
}