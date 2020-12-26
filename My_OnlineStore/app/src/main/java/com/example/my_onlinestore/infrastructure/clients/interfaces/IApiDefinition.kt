package com.example.my_onlinestore.infrastructure.clients.interfaces

import com.example.my_onlinestore.model.Category
import com.example.my_onlinestore.model.server_dto.ServerAttribute
import com.example.my_onlinestore.model.server_dto.ServerCategory
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface IApiDefinition {

    @GET("attributes")
    fun getAttributes(@Header("Authorization") auth: String): Deferred<List<ServerAttribute>>

    @GET("categories/")
    fun getCategory(@Query("id") id: Long): Deferred<Category>

    @GET("categories")
    fun getCategories(@Header("Authorization") auth: String): Deferred<List<ServerCategory>>
}