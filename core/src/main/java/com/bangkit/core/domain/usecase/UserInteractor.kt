package com.bangkit.core.domain.usecase

import com.bangkit.core.domain.repository.IUserRepository

class UserInteractor(private val userRepo: IUserRepository): UserUseCase {
    override fun getUserByUsername(username: String) = userRepo.getUserByUsername(username)

    override fun getDetailUser(id: Int) = userRepo.getDetailUser(id)

    override fun getAllFavoriteUser() = userRepo.getAllFavoriteUser()

    override fun getUserFollowers(id: Int) = userRepo.getUserFollowers(id)

    override fun getUserFollowing(id: Int) = userRepo.getUserFollowing(id)

}