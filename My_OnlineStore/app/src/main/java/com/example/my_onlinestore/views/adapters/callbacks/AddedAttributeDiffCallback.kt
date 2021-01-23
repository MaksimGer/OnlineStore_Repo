package com.example.my_onlinestore.views.adapters.callbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.my_onlinestore.viewmodels.AddedAttributeViewModel
import com.example.my_onlinestore.viewmodels.AttributeViewModel

class AddedAttributeDiffCallback: DiffUtil.ItemCallback<AddedAttributeViewModel>(){
    override fun areItemsTheSame(
            oldItem: AddedAttributeViewModel,
            newItem: AddedAttributeViewModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
            oldItem: AddedAttributeViewModel,
            newItem: AddedAttributeViewModel
    ): Boolean {
        return oldItem.name == newItem.name
                && oldItem.categories == newItem.categories
    }

}