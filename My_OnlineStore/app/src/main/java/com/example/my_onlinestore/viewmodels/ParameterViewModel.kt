package com.example.my_onlinestore.viewmodels

import com.example.my_onlinestore.model.Parameter
import java.io.Serializable

data class ParameterViewModel(
        val parameter: Parameter,
        val id: Long = parameter.id,
        val product: String = parameter.product.name,
        val attribute: String = parameter.attribute.name,
        val value: String = parameter.value
): Serializable