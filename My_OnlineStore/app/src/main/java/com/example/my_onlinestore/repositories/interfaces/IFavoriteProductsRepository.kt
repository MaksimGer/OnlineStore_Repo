package com.example.my_onlinestore.repositories.interfaces

import androidx.lifecycle.LiveData
import com.example.my_onlinestore.model.Product

interface IFavoriteProductsRepository {
    fun getFavoriteProducts(): LiveData<List<Product>>
    suspend fun getFavorite(): List<Product>
    suspend fun putFavoriteProduct(product: Product)
    suspend fun deleteFavoriteProduct(product: Product)
}