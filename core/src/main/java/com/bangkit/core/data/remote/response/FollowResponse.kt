package com.bangkit.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class FollowResponse(

	@field:SerializedName("login")
	val login: String? = null,

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

)
