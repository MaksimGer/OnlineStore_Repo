package com.example.my_onlinestore.repositories.interfaces

import com.example.my_onlinestore.model.Attribute

interface IAttributeRepository {
    suspend fun getAttributes(): List<Attribute>
}