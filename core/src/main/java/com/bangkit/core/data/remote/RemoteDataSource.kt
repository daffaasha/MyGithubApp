package com.bangkit.core.data.remote

import com.bangkit.core.data.remote.network.ApiService
import com.bangkit.core.utils.DataMapper
import kotlinx.coroutines.flow.flow
import com.bangkit.core.data.Result

class RemoteDataSource(private val apiService: ApiService) {

    fun getSearchUser(userName: String) = flow {
        emit(Result.Loading)
        try {
            val response = apiService.getSearchUser(userName)
            val listUser = DataMapper.listUserToDomain(response.items)
            emit(Result.Success(listUser))
        } catch (e: Exception) {
            emit(Result.Error(e.toString()))
        }
    }

    fun getDetailUser(userName: String) = flow {
        emit(Result.Loading)
        try {
            val response = apiService.getDetailUser(userName)
            val user = DataMapper.detailToDomain(response)
            emit(Result.Success(user))
        } catch (e: Exception) {
            emit(Result.Error(e.toString()))
        }
    }


    fun getUserFollower(userName: String) = flow {
        emit(Result.Loading)
        try {
            val response = apiService.getUserFollowers(userName)
            val listUser = DataMapper.followToDomain(response)
            emit(Result.Success(listUser))
        } catch (e: Exception) {
            emit(Result.Error(e.toString()))
        }
    }

    fun getUserFollowing(userName: String) = flow {
        emit(Result.Loading)
        try {
            val response = apiService.getUserFollowing(userName)
            val listUser = DataMapper.followToDomain(response)
            emit(Result.Success(listUser))
        } catch (e: Exception) {
            emit(Result.Error(e.toString()))
        }
    }
}