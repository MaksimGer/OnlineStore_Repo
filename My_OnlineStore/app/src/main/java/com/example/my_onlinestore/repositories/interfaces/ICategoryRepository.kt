package com.example.my_onlinestore.repositories.interfaces

import com.example.my_onlinestore.model.Category
import com.example.my_onlinestore.model.server_dto.ServerCategory

interface ICategoryRepository {
    suspend fun getCategories(): List<Category>
    suspend fun saveCategory(category: ServerCategory): Category
}
