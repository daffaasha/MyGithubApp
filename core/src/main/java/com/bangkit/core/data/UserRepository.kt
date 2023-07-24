package com.bangkit.core.data

import com.bangkit.core.data.local.LocalDataSource
import com.bangkit.core.data.remote.RemoteDataSource
import com.bangkit.core.data.remote.network.ApiResponse
import com.bangkit.core.domain.model.User
import com.bangkit.core.domain.repository.IUserRepository
import com.bangkit.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


class UserRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IUserRepository {

    override suspend fun getUserByUsername(username: String): Flow<Result<List<User>>> {
        return flow {
            remoteDataSource.getSearchUser(username).collect {
                when (it) {
                    is ApiResponse.Success -> emit(Result.Success(it.data))
                    is ApiResponse.Error -> emit(Result.Error(it.errorMessage))
                    is ApiResponse.Empty -> {}
                }
            }
        }
    }

    override suspend fun getDetailUser(username: String): Flow<Result<User>> {
        return flow {
            remoteDataSource.getDetailUser(username).collect {
                when(it) {
                    is ApiResponse.Success -> emit(Result.Success(it.data))
                    is ApiResponse.Error -> emit(Result.Error(it.errorMessage))
                    is ApiResponse.Empty -> {}
                }
            }
        }
    }

    override fun getAllFavoriteUser(): Flow<List<User>> {
        return flow {
            localDataSource.getAllFavorite().map {
                emit(DataMapper.entityToDomain(it))
            }
        }
    }

    override suspend fun getUserFollowers(username: String): Flow<Result<List<User>>> {
        return flow {
            remoteDataSource.getUserFollower(username).collect {
                when(it) {
                    is ApiResponse.Success -> emit(Result.Success(it.data))
                    is ApiResponse.Error -> emit(Result.Error(it.errorMessage))
                    is ApiResponse.Empty -> {}
                }
            }
        }
    }

    override suspend fun getUserFollowing(username: String): Flow<Result<List<User>>> {
        return flow {
            remoteDataSource.getUserFollowing(username).collect {
                when(it) {
                    is ApiResponse.Success -> emit(Result.Success(it.data))
                    is ApiResponse.Error -> emit(Result.Error(it.errorMessage))
                    is ApiResponse.Empty -> {}
                }
            }
        }
    }

    override suspend fun setFavorite(user: User) {
        val data = DataMapper.domainToEntity(user)
        localDataSource.insertFavorite(data)
    }

    override suspend fun deleteFavorite(user: User) {
        val data = DataMapper.domainToEntity(user)
        localDataSource.deleteFavorite(data)
    }
}