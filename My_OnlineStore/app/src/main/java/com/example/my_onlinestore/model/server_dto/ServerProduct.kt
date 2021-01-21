package com.example.my_onlinestore.model.server_dto

import com.google.gson.annotations.SerializedName

data class ServerProduct(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("price") val price: Double,
        @SerializedName("count") val count: Long,
        @SerializedName("category") val category: ServerCategory
)