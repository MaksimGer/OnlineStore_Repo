package com.example.my_onlinestore.model

import com.example.my_onlinestore.model.server_dto.ServerCategory

data class Product(val id: Long, val name: String, val price: Double, val count: Long, val category: ServerCategory)
