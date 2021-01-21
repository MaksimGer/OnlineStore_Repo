package com.example.my_onlinestore.repositories

import com.example.my_onlinestore.infrastructure.clients.interfaces.IServerClient
import com.example.my_onlinestore.model.Product
import com.example.my_onlinestore.repositories.interfaces.IProductRepository

class ProductRepository(private val mServerClient: IServerClient): IProductRepository {
    override suspend fun getProductsByCategory(categoryId: Long): List<Product> {
        return mServerClient.getProductsByCategory(categoryId)
    }
}