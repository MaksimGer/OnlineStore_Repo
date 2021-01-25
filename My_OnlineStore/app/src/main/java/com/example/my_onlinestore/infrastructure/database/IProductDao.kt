package com.example.my_onlinestore.infrastructure.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.room.*
import com.example.my_onlinestore.model.Product
import com.example.my_onlinestore.model.local_dto.*
import com.example.my_onlinestore.model.server_dto.ServerCategory
import java.util.*

@Dao
interface IOnlineStoreDao {

    // ------------------------------------------ CART PRODUCT -------------------------------------
    @Query("SELECT * FROM CartProduct")
    abstract fun cartProducts(): LiveData<List<CartProduct>>

    @Query("SELECT * FROM CartProduct")
    abstract fun cart(): List<CartProduct>

    fun getCartProducts(): LiveData<List<Product>>{
        return Transformations.map(cartProducts()){ list ->
            return@map list.map {
                Product(it.serverId, it.name, it.price, it.count, ServerCategory(-1,"", setOf(), setOf()))
            }
        }
    }

    @Insert
    abstract fun insertCart(product: CartProduct)

    fun insertCartProduct(product: Product){
        insertCart(CartProduct().apply {
            serverId = product.id
            name = product.name
            price = product.price
            count = product.count
            categoryId = product.category.id
        })
        addProductCategory(product)
    }

    @Delete
    abstract fun delete(product: CartProduct)

    fun deleteFromCart(product: Product) {
        cart().firstOrNull {
            it.serverId == product.id && it.name == product.name
        }?.let {
            delete(it)
        }
    }

    // ---------------------------------------- FAVORITE PRODUCT -----------------------------------
    @Query("SELECT * FROM FavoriteProduct")
    abstract fun favoriteProducts(): LiveData<List<FavoriteProduct>>

    fun getFavoriteProducts(): LiveData<List<Product>>{
        return Transformations.map(favoriteProducts()){ list ->
            return@map list.map {
                Product(it.serverId, it.name, it.price, it.count, ServerCategory(-1,"", setOf(), setOf()))
            }
        }
    }

    @Query("SELECT * FROM FavoriteProduct")
    abstract fun favorites(): List<FavoriteProduct>

    @Insert
    abstract fun insertFavorite(product: FavoriteProduct)

    @Transaction
    fun insertFavoriteProduct(product: Product){
        insertFavorite(FavoriteProduct().apply {
            serverId = product.id
            name = product.name
            price = product.price
            count = product.count
            categoryId = product.category.id
        })
        addProductCategory(product)
    }

    @Delete
    abstract fun delete(product: FavoriteProduct)

    fun deleteFromFavorites(product: Product) {
        favorites().firstOrNull {
            it.serverId == product.id && it.name == product.name
        }?.let {
            delete(it)
        }
    }

    // ----------------------------------------- LOCAL CATEGORY ------------------------------------
    @Insert
    abstract fun insertLocalCategory(category: LocalCategory)

    @Query("SELECT * FROM LocalCategory WHERE serverId = :categoryId")
    abstract fun getLocalCategory(categoryId: Long): Optional<LocalCategory>

    fun getServerCategory(categoryId: Long): Optional<ServerCategory>{

        return getLocalCategory(categoryId).map {
            ServerCategory(
                it.serverId,
                it.name,
                getCategoryAttributes(it.serverId).toSet(),
                getCategoryProducts(it.serverId).toSet()
            )
        }
    }

    // ------------------------------------------ HELP TABLES --------------------------------------

    @Query("SELECT attributeId FROM CategoryAttribute WHERE categoryId = :categoryId" )
    abstract fun getCategoryAttributes(categoryId: Long): List<Long>

    @Insert
    abstract fun insertCategoryAttribute(categoryAttribute: CategoryAttribute)

    fun insertAllCategoryAttribute(categoryId: Long, attributes: List<Long>){
        for(attributeId in attributes){
            insertCategoryAttribute(CategoryAttribute().apply {
                this.categoryId = categoryId
                this.attributeId = attributeId
            })
        }
    }

    @Query("SELECT productId FROM CategoryProduct WHERE categoryId = :categoryId" )
    abstract fun getCategoryProducts(categoryId: Long): List<Long>

    @Insert
    abstract fun insertCategoryProduct(categoryProduct: CategoryProduct)

    fun insertAllCategoryProduct(categoryId: Long, products: List<Long>){
        for(productId in products){
            insertCategoryProduct(CategoryProduct().apply {
                this.categoryId = categoryId
                this.productId = productId
            })
        }
    }

    private fun addProductCategory(product: Product){
        if(!getLocalCategory(product.category.id).isPresent) {
            insertLocalCategory(LocalCategory().apply {
                serverId = product.category.id
                name = product.category.name
            })
            insertAllCategoryAttribute(product.category.id, product.category.attributes.toList())
            insertAllCategoryProduct(product.category.id, product.category.products.toList())
        }
    }

    @Query("DELETE FROM CategoryProduct")
    fun deleteFromCategoryProducts()

    @Query("DELETE FROM CategoryAttribute")
    fun deleteFromCategoryAttr()

    @Query("DELETE FROM LocalCategory")
    fun deleteFromCategory()
}


