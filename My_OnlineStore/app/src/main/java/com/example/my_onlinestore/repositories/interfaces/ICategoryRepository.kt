package com.example.my_onlinestore.repositories.interfaces

import com.example.my_onlinestore.model.Category

interface ICategoryRepository {
    suspend fun getCategories(): List<Category>
}
