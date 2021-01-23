package com.example.my_onlinestore.model

import com.example.my_onlinestore.model.server_dto.ServerAttribute
import com.example.my_onlinestore.model.server_dto.ServerProduct
import com.google.gson.annotations.SerializedName

data class Parameter(
        @SerializedName("id") val id: Long,
        @SerializedName("product") val product: ServerProduct,
        @SerializedName("attribute") val attribute: ServerAttribute,
        @SerializedName("value") val value: String
)