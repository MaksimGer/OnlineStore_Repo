package com.example.my_onlinestore.model.local_dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class LocalCategory {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var serverId: Long = -1
    var name: String = ""
}