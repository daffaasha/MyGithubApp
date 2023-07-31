package com.bangkit.core.data.local

import com.bangkit.core.data.local.entity.FavoriteEntity
import com.bangkit.core.data.local.room.FavoriteDao

class LocalDataSource(private val favoriteDao: FavoriteDao) {

    fun getAllFavorite() = favoriteDao.getAllFavorite()

    fun deleteFavorite(username: String) = favoriteDao.deleteFavorite(username)

    fun insertFavorite(favorite: FavoriteEntity) = favoriteDao.insertFavorite(favorite)

    fun checkFavorite(userName: String) = favoriteDao.checkFavorite(userName)

}