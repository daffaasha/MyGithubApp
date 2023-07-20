package com.bangkit.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_user")
data class FavoriteEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var userId: Int,

    @ColumnInfo(name = "username")
    var username: String,

    @ColumnInfo(name = "avatar")
    var avatar: String
)