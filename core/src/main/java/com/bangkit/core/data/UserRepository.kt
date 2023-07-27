package com.bangkit.core.data

import com.bangkit.core.data.local.LocalDataSource
import com.bangkit.core.data.remote.RemoteDataSource
import com.bangkit.core.domain.model.User
import com.bangkit.core.domain.repository.IUserRepository
import com.bangkit.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


class UserRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IUserRepository {

    override fun getUserByUsername(username: String) = remoteDataSource.getSearchUser(username)

    override fun getDetailUser(username: String) = remoteDataSource.getDetailUser(username)

    override fun getAllFavoriteUser(): Flow<List<User>> {
        return flow {
            localDataSource.getAllFavorite().map {
                emit(DataMapper.entityToDomain(it))
            }
        }
    }

    override fun getUserFollowers(username: String) = remoteDataSource.getUserFollower(username)

    override fun getUserFollowing(username: String) = remoteDataSource.getUserFollowing(username)

    override suspend fun setFavorite(user: User) {
        val data = DataMapper.domainToEntity(user)
        localDataSource.insertFavorite(data)
    }

    override suspend fun deleteFavorite(user: User) {
        val data = DataMapper.domainToEntity(user)
        localDataSource.deleteFavorite(data)
    override fun checkFavorite(username: String) = flow {
        localDataSource.checkFavorite(username).map {
            if (it.isEmpty()) {
                emit(emptyList<User>())
            } else {
                emit(DataMapper.entityToDomain(it))
            }
        }
    }
}