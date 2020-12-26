package com.example.my_onlinestore.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.my_onlinestore.repositories.interfaces.IAttributeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AttributeListVieModel@ViewModelInject constructor(
    private val mRepository: IAttributeRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val mAttributeList = MutableLiveData<List<AttributeViewModel>>()
    private val mIsLoading = MutableLiveData<Boolean>(true)

    val attributeList: LiveData<List<AttributeViewModel>>
        get() = mAttributeList

    val isLoading: LiveData<Boolean>
        get() = mIsLoading

    init{
        viewModelScope.launch(Dispatchers.IO) {
            mIsLoading.postValue(true)
            mAttributeList.postValue(mRepository.getAttributes().map { attribute ->
                AttributeViewModel(attribute)
            })
            mIsLoading.postValue(false)
        }
    }
}