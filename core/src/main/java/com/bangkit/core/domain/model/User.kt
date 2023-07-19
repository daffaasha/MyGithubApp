package com.bangkit.core.domain.model

data class User(
    val name: String,
    val username: String,
    val followers: Int,
    val following: Int
)