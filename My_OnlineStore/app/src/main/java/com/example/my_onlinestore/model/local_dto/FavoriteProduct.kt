package com.example.my_onlinestore.model.local_dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class FavoriteProduct {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var serverId: Long = -1
    var name: String = "unknown"
    var price: Double = 1.0
    var count: Long = 0
    var categoryId: Long = -1
}