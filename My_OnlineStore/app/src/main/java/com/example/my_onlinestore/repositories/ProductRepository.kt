package com.example.my_onlinestore.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.my_onlinestore.infrastructure.clients.interfaces.IServerClient
import com.example.my_onlinestore.infrastructure.database.ProductDatabase
import com.example.my_onlinestore.model.Product
import com.example.my_onlinestore.model.local_dto.CartProduct
import com.example.my_onlinestore.model.local_dto.FavoriteProduct
import com.example.my_onlinestore.model.server_dto.ServerCategory
import com.example.my_onlinestore.repositories.interfaces.ICartProductsRepository
import com.example.my_onlinestore.repositories.interfaces.ICategoryRepository
import com.example.my_onlinestore.repositories.interfaces.IFavoriteProductsRepository
import com.example.my_onlinestore.repositories.interfaces.IProductRepository

class ProductRepository(
    private val mServerClient: IServerClient,
    private val mProductDatabase: ProductDatabase,
    private val mCategoryRepository: ICategoryRepository
): IProductRepository, ICartProductsRepository, IFavoriteProductsRepository {

    override suspend fun getProductsByCategory(categoryId: Long): List<Product> {
        return mServerClient.getProductsByCategory(categoryId)
    }

    override fun getCartProducts(): LiveData<List<Product>> {
        return mProductDatabase.dao().getCartProducts()
    }

    override suspend fun putCartProduct(product: Product) {
        mProductDatabase.dao().insertCartProduct(product)
    }

    override suspend fun deleteCartProduct(product: Product) {
        mProductDatabase.dao().deleteFromCart(product)
    }

    override fun getFavoriteProducts(): LiveData<List<Product>> {
        return mProductDatabase.dao().getFavoriteProducts()
    }

    override suspend fun getFavorite(): List<Product> {
        return mProductDatabase.dao().favorites().map {

            val category = mCategoryRepository.getCategory(it.categoryId)
            Product(it.serverId, it.name, it.price, it.count, category)
        }
    }

    override suspend fun putFavoriteProduct(product: Product) {
        mProductDatabase.dao().insertFavoriteProduct(product)
    }

    override suspend fun deleteFavoriteProduct(product: Product) {
        mProductDatabase.dao().deleteFromFavorites(product)
    }
}