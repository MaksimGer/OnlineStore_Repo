package com.example.my_onlinestore.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.my_onlinestore.databinding.ProductItemBinding
import com.example.my_onlinestore.repositories.interfaces.ICategoryRepository
import com.example.my_onlinestore.repositories.interfaces.IProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductListViewModel@ViewModelInject constructor(
        private val mRepository: IProductRepository,
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

            mProductList.postValue(mRepository.getProductsByCategory(categoryId).map { product ->
                ProductViewModel(product)
            })
            mIsLoading.postValue(false)
        }
    }

    fun onBuyProduct(product: ProductViewModel) {
        val i: Long = 1;
    }

    fun onLikeProduct(product: ProductViewModel) {
        product.isLike = !product.isLike
    }
}