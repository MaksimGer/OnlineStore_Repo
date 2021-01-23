package com.example.my_onlinestore.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.my_onlinestore.repositories.interfaces.IParameterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ParameterListViewModel@ViewModelInject constructor(
        private val mRepository: IParameterRepository,
        @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val mParameterList = MutableLiveData<List<ParameterViewModel>>()
    private val mIsLoading = MutableLiveData<Boolean>(true)

    val parameterList: LiveData<List<ParameterViewModel>>
        get() = mParameterList

    val isLoading: LiveData<Boolean>
        get() = mIsLoading

    fun downloadParameters(productId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            mIsLoading.postValue(true)

            mParameterList.postValue(mRepository.getParametersByProduct(productId).map { parameter ->
                ParameterViewModel(parameter)
            })
            mIsLoading.postValue(false)
        }
    }
}