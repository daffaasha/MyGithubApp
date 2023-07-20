package com.bangkit.core.data.local.room

import androidx.room.Database
import com.bangkit.core.data.local.entity.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase {

    abstract fun favoriteDao():FavoriteDao

}