package com.bangkit.core.data.remote

import com.bangkit.core.data.remote.network.ApiResponse
import com.bangkit.core.data.remote.network.ApiService
import com.bangkit.core.domain.model.User
import com.bangkit.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getSearchUser(userName: String): Flow<ApiResponse<List<User>>> {
        return flow {
            try {
                val response = apiService.getSearchUser(userName)
                if (response.items.isNotEmpty()) {
                    val listUser = DataMapper.listUserToDomain(response.items)
                    emit(ApiResponse.Success(listUser))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }
    }

    suspend fun getDetailUser(userName: String): Flow<ApiResponse<User>> {
        return flow {
            try {
                val response = apiService.getDetailUser(userName)
                val user = DataMapper.detailToDomain(response)
                emit(ApiResponse.Success(user))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }
    }

    suspend fun getUserFollower(userName: String): Flow<ApiResponse<List<User>>> {
        return flow {
            try {
                val response = apiService.getUserFollowers(userName)
                if (response.isNotEmpty()) {
                    val listUser = DataMapper.followToDomain(response)
                    emit(ApiResponse.Success(listUser))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }
    }

    suspend fun getUserFollowing(userName: String): Flow<ApiResponse<List<User>>> {
        return flow {
            try {
                val response = apiService.getUserFollowing(userName)
                if (response.isNotEmpty()) {
                    val listUser = DataMapper.followToDomain(response)
                    emit(ApiResponse.Success(listUser))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }
    }
}