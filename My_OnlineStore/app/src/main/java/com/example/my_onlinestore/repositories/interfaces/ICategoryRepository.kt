package com.example.my_onlinestore.repositories.interfaces

import com.example.my_onlinestore.model.Category
import com.example.my_onlinestore.model.server_dto.ServerCategory

interface ICategoryRepository {
    suspend fun getCategories(): List<Category>
    suspend fun getCategory(categoryId: Long): ServerCategory
    suspend fun saveCategory(category: ServerCategory): Category
    suspend fun deleteCategory(category: ServerCategory): String
}
