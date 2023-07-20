package com.bangkit.core.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bangkit.core.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(user: FavoriteEntity)

    @Delete
    fun deleteFavorite(user: FavoriteEntity)

    @Query("SELECT * FROM favorite_user")
    fun getAllFavorite(): Flow<List<FavoriteEntity>>

}