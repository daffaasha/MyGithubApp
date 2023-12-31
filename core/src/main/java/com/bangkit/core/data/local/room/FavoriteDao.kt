package com.bangkit.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bangkit.core.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(user: FavoriteEntity)

    @Query("DELETE FROM favorite_user WHERE username = :userName")
    fun deleteFavorite(userName: String)

    @Query("SELECT * FROM favorite_user")
    fun getAllFavorite(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM favorite_user WHERE username = :userName")
    fun checkFavorite(userName: String): Flow<List<FavoriteEntity>>
}