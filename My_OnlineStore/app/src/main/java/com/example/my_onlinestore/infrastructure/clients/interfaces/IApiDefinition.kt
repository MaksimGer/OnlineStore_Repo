package com.example.my_onlinestore.infrastructure.clients.interfaces

import com.example.my_onlinestore.model.Category
import com.example.my_onlinestore.model.Parameter
import com.example.my_onlinestore.model.server_dto.ServerAttribute
import com.example.my_onlinestore.model.server_dto.ServerCategory
import com.example.my_onlinestore.model.server_dto.ServerProduct
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface IApiDefinition {

    @GET("attributes")
    fun getAttributes(@Header("Authorization") auth: String): Deferred<List<ServerAttribute>>

    @GET("categories/")
    fun getCategory(@Header("Authorization") auth: String, @Query("id") id: Long): Deferred<ServerCategory>

    @GET("categories")
    fun getCategories(@Header("Authorization") auth: String): Deferred<List<ServerCategory>>

    @GET("categories/products/")
    fun getProductsByCategory(
            @Header("Authorization") auth: String,
            @Query("id") categoryId: Long
    ): Deferred<List<ServerProduct>>

    @GET("products")
    fun getAllProducts(@Header("Authorization") auth: String): Deferred<List<ServerProduct>>

    @GET("products/parameters/")
    fun getParametersByProduct(
            @Header("Authorization") auth: String,
            @Query("id") productId: Long
    ): Deferred<List<Parameter>>

    @POST("categories")
    fun addCategory(
            @Header("Authorization") auth: String,
            @Body newCategory: ServerCategory
    ): Deferred<ServerCategory>
}