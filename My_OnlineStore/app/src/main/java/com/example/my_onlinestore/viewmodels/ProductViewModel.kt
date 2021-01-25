package com.example.my_onlinestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.my_onlinestore.model.Product
import com.example.my_onlinestore.model.server_dto.ServerCategory
import java.io.Serializable

data class ProductViewModel(
        val product: Product,
        val id: Long = product.id,
        val name: String = product.name,
        val price: Double = product.price,
        val count: Long = product.count,
        val category: ServerCategory = product.category,
        var isLike: Boolean = false
): Serializable