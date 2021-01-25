package com.example.my_onlinestore.repositories

import com.example.my_onlinestore.infrastructure.clients.interfaces.IServerClient
import com.example.my_onlinestore.model.Category
import com.example.my_onlinestore.model.server_dto.ServerCategory
import com.example.my_onlinestore.repositories.interfaces.ICategoryRepository

class CategoryRepository(private val mServerClient: IServerClient): ICategoryRepository {
    override suspend fun getCategories(): List<Category> {
        return mServerClient.getCategorise()
    }

    override suspend fun getCategory(categoryId: Long): ServerCategory {
        return mServerClient.getCategory(categoryId)
    }

    override suspend fun saveCategory(category: ServerCategory): Category {
        return mServerClient.saveCategory(category)
    }
}