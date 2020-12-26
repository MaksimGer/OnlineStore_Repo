package com.example.my_onlinestore.model

data class Category(val id: Long, val name: String, val attributes: Set<Attribute>, val products: Set<Product>)