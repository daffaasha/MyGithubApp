package com.bangkit.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bangkit.core.data.local.entity.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase: RoomDatabase() {

    abstract fun favoriteDao():FavoriteDao

}