package com.example.my_onlinestore.repositories.interfaces

import com.example.my_onlinestore.model.Parameter

interface IParameterRepository {
    suspend fun getParametersByProduct(productId: Long): List<Parameter>
}