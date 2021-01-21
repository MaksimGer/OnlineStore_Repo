package com.example.my_onlinestore.repositories.interfaces

import com.example.my_onlinestore.model.Product

interface IProductRepository {
    suspend fun getProductsByCategory(categoryId: Long): List<Product>
}