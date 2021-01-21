package com.example.my_onlinestore.views.adapters.callbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.my_onlinestore.viewmodels.ProductViewModel

class ProductDiffCallback: DiffUtil.ItemCallback<ProductViewModel>(){
    override fun areItemsTheSame(
            oldItem: ProductViewModel,
            newItem: ProductViewModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
            oldItem: ProductViewModel,
            newItem: ProductViewModel
    ): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.category == newItem.category &&
                oldItem.price == newItem.price &&
                oldItem.count == newItem.count
    }
}