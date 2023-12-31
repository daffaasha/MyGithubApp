package com.bangkit.core.data

import com.bangkit.core.data.local.LocalDataSource
import com.bangkit.core.data.remote.RemoteDataSource
import com.bangkit.core.domain.model.User
import com.bangkit.core.domain.repository.IUserRepository
import com.bangkit.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class UserRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IUserRepository {

    override fun getUserByUsername(username: String) = remoteDataSource.getSearchUser(username)

    override fun getDetailUser(username: String) = remoteDataSource.getDetailUser(username)

    override fun getAllFavoriteUser(): Flow<List<User>> {
        return localDataSource.getAllFavorite().map {
            DataMapper.entityToDomain(it)
        }
    }

    override fun getUserFollowers(username: String) = remoteDataSource.getUserFollower(username)

    override fun getUserFollowing(username: String) = remoteDataSource.getUserFollowing(username)

    override fun setFavorite(user: User) {
        val data = DataMapper.domainToEntity(user)
        localDataSource.insertFavorite(data)
    }

    override fun deleteFavorite(username: String) {
        localDataSource.deleteFavorite(username)
    }

    override fun checkFavorite(username: String) =
        localDataSource.checkFavorite(username).map {
            DataMapper.entityToDomain(it)
        }
}