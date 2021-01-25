package com.example.my_onlinestore.infrastructure.di

import android.app.Application
import androidx.room.Room
import com.example.my_onlinestore.infrastructure.clients.ServerClient
import com.example.my_onlinestore.infrastructure.clients.interfaces.IServerClient
import com.example.my_onlinestore.infrastructure.database.ProductDatabase
import com.example.my_onlinestore.repositories.AttributeRepository
import com.example.my_onlinestore.repositories.CategoryRepository
import com.example.my_onlinestore.repositories.ParameterRepository
import com.example.my_onlinestore.repositories.ProductRepository
import com.example.my_onlinestore.repositories.interfaces.*
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
    @Singleton
    fun provideProductRepo(serverClient: IServerClient, productDatabase: ProductDatabase, categoryRepository: ICategoryRepository): ProductRepository {
        return ProductRepository(serverClient, productDatabase, categoryRepository)
    }

    @Provides
    @Singleton
    fun provideIProductProductRepo(productRepository: ProductRepository): IProductRepository {
        return productRepository
    }

    @Provides
    @Singleton
    fun provideICartProductRepo(productRepository: ProductRepository): ICartProductsRepository {
        return productRepository
    }

    @Provides
    @Singleton
    fun provideIFavoriteProductRepo(productRepository: ProductRepository): IFavoriteProductsRepository {
        return productRepository
    }

    @Provides
    fun provideParameterRepo(serverClient: IServerClient): IParameterRepository {
        return ParameterRepository(serverClient)
    }

    @Provides
    fun provideRoom(application: Application): ProductDatabase {
        return Room.databaseBuilder(
            application, ProductDatabase::class.java,
            "LocalProductDatabase"
        ).build()
    }
}