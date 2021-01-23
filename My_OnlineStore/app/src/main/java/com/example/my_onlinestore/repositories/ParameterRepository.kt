package com.example.my_onlinestore.repositories

import com.example.my_onlinestore.infrastructure.clients.interfaces.IServerClient
import com.example.my_onlinestore.model.Parameter
import com.example.my_onlinestore.repositories.interfaces.IParameterRepository

class ParameterRepository (private val mServerClient: IServerClient): IParameterRepository {
    override suspend fun getParametersByProduct(productId: Long): List<Parameter> {
        return mServerClient.getParametersByProduct(productId)
    }
}
