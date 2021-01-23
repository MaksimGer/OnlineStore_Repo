package com.example.my_onlinestore.repositories

import com.example.my_onlinestore.infrastructure.clients.interfaces.IServerClient
import com.example.my_onlinestore.model.Attribute
import com.example.my_onlinestore.repositories.interfaces.IAttributeRepository

class AttributeRepository(private val mServerClient: IServerClient): IAttributeRepository {
    override suspend fun getAttributes(): List<Attribute> {
        return mServerClient.getAttributes()
    }
}
