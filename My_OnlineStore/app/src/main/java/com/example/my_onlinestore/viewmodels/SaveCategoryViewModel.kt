package com.example.my_onlinestore.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.my_onlinestore.model.Category
import com.example.my_onlinestore.model.server_dto.ServerCategory
import com.example.my_onlinestore.repositories.interfaces.IAttributeRepository
import com.example.my_onlinestore.repositories.interfaces.ICategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class SaveCategoryViewModel @ViewModelInject constructor(
        @Assisted private val savedStateHandle: SavedStateHandle,
        private val mRepository: ICategoryRepository,
        private val mAttributeRepository: IAttributeRepository
) : ViewModel() {
    private val mIsLoading = MutableLiveData<Boolean>(true)
    private val mOnCategorySaved = MediatorLiveData<Boolean>()
    val categoryName =  MutableLiveData<String> ("Category name")
//    val attributes = MutableLiveData<Set<Long>>() //!!!!
    private val mAttributes = MutableLiveData<List<AddedAttributeViewModel>>()

    val onCategorySaved: LiveData<Boolean>
        get() = mOnCategorySaved

//    val categoryName: LiveData<String>
//        get() = mCategoryName

    val attributes: LiveData<List<AddedAttributeViewModel>>
        get() = mAttributes

    val isLoading: LiveData<Boolean>
        get() = mIsLoading

    init{
        viewModelScope.launch(Dispatchers.IO) {
            mIsLoading.postValue(true)
            mAttributes.postValue(mAttributeRepository.getAttributes().map { attribute ->
                AddedAttributeViewModel(attribute)
            })
            mIsLoading.postValue(false)
        }
    }

    fun onSave(){
        viewModelScope.launch (Dispatchers.IO) {
            val attributesId = hashSetOf<Long>()

            for(attibute in mAttributes.value?: listOf() ){
                if(attibute.isAdded.value?: (false))
                    attributesId.add(attibute.id)
            }

            try {
                val newCategory: ServerCategory = ServerCategory(0, categoryName.value?:"NewCategory", attributesId, setOf())
                mRepository.saveCategory(newCategory)
                mOnCategorySaved.postValue(true)
            } catch (e: Exception) {
                mOnCategorySaved.postValue(false)
            }
        }
    }
}