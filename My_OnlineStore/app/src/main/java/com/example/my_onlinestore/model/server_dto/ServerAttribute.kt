package com.example.my_onlinestore.model.server_dto

import com.google.gson.annotations.SerializedName

data class ServerAttribute(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("categories") val categories: Set<Long>
)