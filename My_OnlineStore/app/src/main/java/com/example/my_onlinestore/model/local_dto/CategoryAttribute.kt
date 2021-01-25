package com.example.my_onlinestore.model.local_dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CategoryAttribute {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var categoryId: Long = -1
    var attributeId: Long = -1
}