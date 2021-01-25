package com.example.my_onlinestore.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.my_onlinestore.databinding.ProductItemBinding
import com.example.my_onlinestore.model.Product
import com.example.my_onlinestore.model.server_dto.ServerCategory
import com.example.my_onlinestore.repositories.interfaces.ICategoryRepository
import com.example.my_onlinestore.repositories.interfaces.IFavoriteProductsRepository
import com.example.my_onlinestore.repositories.interfaces.IProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductListViewModel@ViewModelInject constructor(
        private val mRepository: IProductRepository,
        private val mFavoriteProductsRepository: IFavoriteProductsRepository,
        private val mCategroyRepository: ICategoryRepository,
        @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val mProductList = MutableLiveData<List<ProductViewModel>>()
    private val mIsLoading = MutableLiveData<Boolean>(true)

    val productList: LiveData<List<ProductViewModel>>
        get() = mProductList

    val isLoading: LiveData<Boolean>
        get() = mIsLoading

    fun downloadProducts(categoryId: Long){
        viewModelScope.launch(Dispatchers.IO) {
            mIsLoading.postValue(true)

            val favoritesProducts = mFavoriteProductsRepository.getFavorite()
            setCategories(favoritesProducts)

            mProductList.postValue(mRepository.getProductsByCategory(categoryId).map { product ->
                ProductViewModel(
                    product,
                    product.id,
                    product.name,
                    product.price,
                    product.count,
                    product.category,
                    favoritesProducts.contains(product))
            })
            mIsLoading.postValue(false)
        }
    }

    fun onBuyProduct(product: ProductViewModel) {
        val i: Long = 1;
    }

    fun likeProduct(product: ProductViewModel) {
        viewModelScope.launch(Dispatchers.IO) {
            mFavoriteProductsRepository.putFavoriteProduct(product.product)
        }
        product.isLike = true
    }

    fun unLikeProduct(product: ProductViewModel){
        viewModelScope.launch(Dispatchers.IO) {
            mFavoriteProductsRepository.deleteFavoriteProduct(product.product)
        }
        product.isLike = false
    }

    fun deleteCategory(category: CategoryViewModel){
        viewModelScope.launch(Dispatchers.IO) {
            mCategroyRepository.deleteCategory(
                ServerCategory(
                    category.category.id,
                    category.category.name,
                    setOf(),
                    setOf()
                )
            )
        }
    }

    private fun setCategories(products: List<Product>){
        viewModelScope.launch(Dispatchers.IO) {
            for (product in products){
                val category = mCategroyRepository.getCategory(product.category.id)
                product.category = category
            }
        }
    }

}