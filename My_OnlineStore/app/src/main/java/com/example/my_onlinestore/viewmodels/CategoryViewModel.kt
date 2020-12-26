package com.example.my_onlinestore.viewmodels

import com.example.my_onlinestore.model.Attribute
import com.example.my_onlinestore.model.Category
import com.example.my_onlinestore.model.Product

data class CategoryViewModel(
        val category: Category,
        val id: String = category.id.toString(),
        val name: String = category.name,
        val attributes: Set<Attribute> = category.attributes,
        val products: Set<Product> = category.products
)
