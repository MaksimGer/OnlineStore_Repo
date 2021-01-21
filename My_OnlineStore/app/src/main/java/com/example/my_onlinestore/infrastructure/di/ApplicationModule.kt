package com.example.my_onlinestore.infrastructure.di

import com.example.my_onlinestore.infrastructure.clients.ServerClient
import com.example.my_onlinestore.infrastructure.clients.interfaces.IServerClient
import com.example.my_onlinestore.repositories.AttributeRepository
import com.example.my_onlinestore.repositories.CategoryRepository
import com.example.my_onlinestore.repositories.ProductRepository
import com.example.my_onlinestore.repositories.interfaces.IAttributeRepository
import com.example.my_onlinestore.repositories.interfaces.ICategoryRepository
import com.example.my_onlinestore.repositories.interfaces.IProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideServerClient(): IServerClient {
        return ServerClient()
    }

    @Provides
    fun provideRepo(serverClient: IServerClient): IAttributeRepository{
        return AttributeRepository(serverClient)
    }

    @Provides
    fun provideCategoryRepo(serverClient: IServerClient): ICategoryRepository {
        return CategoryRepository(serverClient)
    }

    @Provides
    fun provideProductRepo(serverClient: IServerClient): IProductRepository {
        return ProductRepository(serverClient)
    }
}