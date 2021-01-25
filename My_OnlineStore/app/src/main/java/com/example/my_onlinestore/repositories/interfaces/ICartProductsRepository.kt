package com.example.my_onlinestore.repositories.interfaces

import androidx.lifecycle.LiveData
import com.example.my_onlinestore.model.Product

interface ICartProductsRepository {
    fun getCartProducts(): LiveData<List<Product>>
    suspend fun putCartProduct(product: Product)
    suspend fun deleteCartProduct(product: Product)
}