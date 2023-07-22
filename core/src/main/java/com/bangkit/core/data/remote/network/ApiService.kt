package com.bangkit.core.data.remote.network

import com.bangkit.core.data.remote.response.DetailResponse
import com.bangkit.core.data.remote.response.FollowResponse
import com.bangkit.core.data.remote.response.ListUserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    suspend fun getSearchUser(
        @Query("q") user: String
    ): ListUserResponse

    @GET("users/{username}")
    suspend fun getDetailUser(
        @Path("username") username: String
    ): DetailResponse

    @GET("users/{username}/followers")
    suspend fun getUserFollowers(
        @Path("username") username: String
    ): List<FollowResponse>

    @GET("users/{username}/following")
    suspend fun getUserFollowing(
        @Path("username") username: String
    ): List<FollowResponse>


}