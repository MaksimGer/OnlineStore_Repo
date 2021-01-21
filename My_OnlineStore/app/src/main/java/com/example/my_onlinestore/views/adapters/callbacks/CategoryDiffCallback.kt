package com.example.my_onlinestore.views.adapters.callbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.my_onlinestore.viewmodels.AttributeViewModel
import com.example.my_onlinestore.viewmodels.CategoryViewModel

class CategoryDiffCallback : DiffUtil.ItemCallback<CategoryViewModel>(){
    override fun areItemsTheSame(
            oldItem: CategoryViewModel,
            newItem: CategoryViewModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
            oldItem: CategoryViewModel,
            newItem: CategoryViewModel
    ): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.attributes == newItem.attributes &&
                oldItem.products == newItem.products
    }

}