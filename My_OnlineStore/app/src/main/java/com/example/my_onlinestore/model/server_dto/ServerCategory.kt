package com.example.my_onlinestore.model.server_dto

import com.google.gson.annotations.SerializedName

data class ServerCategory (
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("attributes") val attributes: Set<Long>,
    @SerializedName("products") val products: Set<Long>
)