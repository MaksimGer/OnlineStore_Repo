package com.example.my_onlinestore.views.adapters.callbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.my_onlinestore.viewmodels.AttributeViewModel

class AttributeDiffCallback : DiffUtil.ItemCallback<AttributeViewModel>(){
    override fun areItemsTheSame(
        oldItem: AttributeViewModel,
        newItem: AttributeViewModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: AttributeViewModel,
        newItem: AttributeViewModel
    ): Boolean {
        return oldItem.name == newItem.name && oldItem.categories == newItem.categories
    }

}
