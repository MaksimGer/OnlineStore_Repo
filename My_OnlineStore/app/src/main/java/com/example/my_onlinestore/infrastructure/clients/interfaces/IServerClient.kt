package com.example.my_onlinestore.infrastructure.clients.interfaces

import com.example.my_onlinestore.model.Attribute
import com.example.my_onlinestore.model.Category
import com.example.my_onlinestore.model.Parameter
import com.example.my_onlinestore.model.Product
import com.example.my_onlinestore.model.server_dto.ServerCategory

interface IServerClient {
    suspend fun getAttributes(): List<Attribute>
    suspend fun getCategorise(): List<Category>
    suspend fun getCategory(categoryId: Long): ServerCategory
    suspend fun getProductsByCategory(categoryId: Long): List<Product>
    suspend fun getParametersByProduct(productId: Long): List<Parameter>
    suspend fun saveCategory(newCategory: ServerCategory): Category
    suspend fun deleteCategory(category: ServerCategory): String
}