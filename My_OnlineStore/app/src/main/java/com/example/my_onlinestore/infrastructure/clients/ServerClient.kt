package com.example.my_onlinestore.infrastructure.clients

import com.example.my_onlinestore.infrastructure.clients.interfaces.IApiDefinition
import com.example.my_onlinestore.infrastructure.clients.interfaces.IServerClient
import com.example.my_onlinestore.model.Attribute
import com.example.my_onlinestore.model.Category
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
}