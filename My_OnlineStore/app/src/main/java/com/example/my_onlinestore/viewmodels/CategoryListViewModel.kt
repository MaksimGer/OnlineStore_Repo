package com.example.my_onlinestore.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.my_onlinestore.repositories.interfaces.IAttributeRepository
import com.example.my_onlinestore.repositories.interfaces.ICategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryListViewModel@ViewModelInject constructor(
        private val mRepository: ICategoryRepository,
        @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val mCategoryList = MutableLiveData<List<CategoryViewModel>>()
    private val mIsLoading = MutableLiveData<Boolean>(true)

    val categoryList: LiveData<List<CategoryViewModel>>
        get() = mCategoryList

    val isLoading: LiveData<Boolean>
        get() = mIsLoading

    init{
        viewModelScope.launch(Dispatchers.IO) {
            mIsLoading.postValue(true)
            mCategoryList.postValue(mRepository.getCategories().map { category ->
                CategoryViewModel(category)
            })
            mIsLoading.postValue(false)
        }
    }
}