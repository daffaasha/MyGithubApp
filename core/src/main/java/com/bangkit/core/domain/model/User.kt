package com.bangkit.core.domain.model

data class User(
    val name: String? = null,
    val username: String,
    val followers: Int? = null,
    val following: Int? = null,
    val avatar: String,
)