package com.example.my_onlinestore.infrastructure.clients

import com.example.my_onlinestore.infrastructure.clients.interfaces.IApiDefinition
import com.example.my_onlinestore.infrastructure.clients.interfaces.IServerClient
import com.example.my_onlinestore.model.Attribute
import com.example.my_onlinestore.model.Category
import com.example.my_onlinestore.model.Parameter
import com.example.my_onlinestore.model.Product
import com.example.my_onlinestore.model.server_dto.ServerCategory
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerClient: IServerClient {

    companion object {
        private const val BASE_URL = "https://api-online-store-mg.herokuapp.com/"
        private const val ADMIN_AUTHENTICATION = "Basic YWRtaW46YWRtaW4="
    }

    private val mService: IApiDefinition

    init {
        val gson = GsonBuilder().create()

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build()
        mService = retrofit.create(IApiDefinition::class.java)

    }

    override suspend fun getAttributes(): List<Attribute> {
        val auth: String = ADMIN_AUTHENTICATION

        return mService.getAttributes(auth).await().map { serverAttr ->
            Attribute(serverAttr.id, serverAttr.name, setOf())
        }
    }

    override suspend fun getCategorise(): List<Category> {
        val auth: String = ADMIN_AUTHENTICATION

        return mService.getCategories(auth).await().map {serverCategory ->
            Category(serverCategory.id, serverCategory.name, setOf(), setOf())
        }
    }

    override suspend fun getCategory(categoryId: Long): ServerCategory {
        val auth: String = ADMIN_AUTHENTICATION

        val serverCategory: ServerCategory  = mService.getCategory(auth, categoryId).await()

        return serverCategory
    }

    override suspend fun getProductsByCategory(categoryId: Long): List<Product> {
        val auth: String = ADMIN_AUTHENTICATION
        val unknownCategory: Long = 0;

        if(categoryId == unknownCategory)
            return mService.getAllProducts(auth).await().map { serverProduct ->
                Product(serverProduct.id, serverProduct.name, serverProduct.price, serverProduct.count, serverProduct.category)
            }

        return  mService.getProductsByCategory(auth, categoryId).await().map { serverProduct ->
            Product(serverProduct.id, serverProduct.name, serverProduct.price, serverProduct.count, serverProduct.category)
        }
    }

    override suspend fun getParametersByProduct(productId: Long): List<Parameter> {
        val auth: String = ADMIN_AUTHENTICATION

        return  mService.getParametersByProduct(auth, productId).await()
    }

    override suspend fun saveCategory(newCategory: ServerCategory): Category {
        val auth: String = ADMIN_AUTHENTICATION

        val createdCategory: ServerCategory = mService.addCategory(auth, newCategory).await()

        return Category(createdCategory.id, createdCategory.name, setOf(), setOf())
    }
}