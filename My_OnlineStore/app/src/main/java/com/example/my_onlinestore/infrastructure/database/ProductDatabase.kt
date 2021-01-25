package com.example.my_onlinestore.infrastructure.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.my_onlinestore.model.local_dto.*

@Database(entities = [CartProduct::class, FavoriteProduct::class, LocalCategory::class, CategoryAttribute::class, CategoryProduct::class], version = 1 )
abstract class ProductDatabase : RoomDatabase() {
    abstract fun dao(): IOnlineStoreDao
}