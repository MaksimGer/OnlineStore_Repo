package com.example.my_onlinestore.infrastructure.clients.interfaces

import com.example.my_onlinestore.model.Attribute
import com.example.my_onlinestore.model.Category

interface IServerClient {
    suspend fun getAttributes(): List<Attribute>
    suspend fun getCategorise(): List<Category>
}