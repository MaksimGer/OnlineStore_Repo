package com.example.my_onlinestore.model

import com.example.my_onlinestore.model.server_dto.ServerAttribute
import com.example.my_onlinestore.model.server_dto.ServerProduct

data class Category(val id: Long, val name: String, val attributes: Set<ServerAttribute>, val products: Set<Product>)
