package com.example.my_onlinestore.infrastructure.clients.interfaces

import com.example.my_onlinestore.model.Attribute
import com.example.my_onlinestore.model.Category
import com.example.my_onlinestore.model.Product

interface IServerClient {
    suspend fun getAttributes(): List<Attribute>
    suspend fun getCategorise(): List<Category>
    suspend fun getCategory(categoryId: Long): Category
    suspend fun getProductsByCategory(categoryId: Long): List<Product>
}