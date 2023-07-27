package com.bangkit.core.data.local

import com.bangkit.core.data.local.entity.FavoriteEntity
import com.bangkit.core.data.local.room.FavoriteDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val favoriteDao: FavoriteDao) {

    fun getAllFavorite(): Flow<List<FavoriteEntity>> = favoriteDao.getAllFavorite()

    fun deleteFavorite(favorite: FavoriteEntity) = favoriteDao.deleteFavorite(favorite)

    fun insertFavorite(favorite: FavoriteEntity) = favoriteDao.insertFavorite(favorite)

    fun checkFavorite(userName: String) = favoriteDao.checkFavorite(userName)

}