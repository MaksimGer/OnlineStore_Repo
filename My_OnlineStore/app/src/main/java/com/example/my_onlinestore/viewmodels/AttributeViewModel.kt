package com.example.my_onlinestore.viewmodels

import com.example.my_onlinestore.model.Attribute
import com.example.my_onlinestore.model.Category

data class AttributeViewModel(val attribute: Attribute, val id: String = attribute.id.toString(), val name: String = attribute.name, val categories: Set<Category> = attribute.categories)