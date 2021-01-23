package com.example.my_onlinestore.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.my_onlinestore.model.Attribute
import com.example.my_onlinestore.model.server_dto.ServerCategory
import java.io.Serializable

data class AddedAttributeViewModel (
        val attribute: Attribute,
        val id: Long = attribute.id,
        val name: String = attribute.name,
        val categories: Set<ServerCategory> = attribute.categories,
        val isAdded: MutableLiveData<Boolean> = MutableLiveData(false)
):Serializable