package com.example.my_onlinestore.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.my_onlinestore.repositories.interfaces.IFavoriteProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteProductListViewModel @ViewModelInject constructor(
    private val mRepository: IFavoriteProductsRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val productList: LiveData<List<ProductViewModel>>
        get() = Transformations.map(mRepository.getFavoriteProducts()){productList ->
            return@map productList.map {product ->
                ProductViewModel(
                    product,
                    product.id,
                    product.name,
                    product.price,
                    product.count,
                    product.category,
                    isLike = true)
            }
        }

    fun unLikeProduct(product: ProductViewModel){
        viewModelScope.launch(Dispatchers.IO) {
            mRepository.deleteFavoriteProduct(product.product)
        }
    }
}