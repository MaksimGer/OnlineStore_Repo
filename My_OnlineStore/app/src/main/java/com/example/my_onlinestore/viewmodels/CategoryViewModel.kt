package com.example.my_onlinestore.viewmodels

import com.example.my_onlinestore.model.Category
import com.example.my_onlinestore.model.Product
import com.example.my_onlinestore.model.server_dto.ServerAttribute
import java.io.Serializable

data class CategoryViewModel(
        val category: Category,
        val id: Long = category.id,
        val name: String = category.name,
        val attributes: Set<ServerAttribute> = category.attributes,
        val products: Set<Product> = category.products
): Serializable
