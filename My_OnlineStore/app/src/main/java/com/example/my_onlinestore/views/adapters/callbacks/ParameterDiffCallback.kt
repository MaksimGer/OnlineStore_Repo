package com.example.my_onlinestore.views.adapters.callbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.my_onlinestore.viewmodels.ParameterViewModel
import com.example.my_onlinestore.viewmodels.ProductViewModel

class ParameterDiffCallback: DiffUtil.ItemCallback<ParameterViewModel>(){
    override fun areItemsTheSame(
            oldItem: ParameterViewModel,
            newItem: ParameterViewModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
            oldItem: ParameterViewModel,
            newItem: ParameterViewModel
    ): Boolean {
        return oldItem.attribute == newItem.attribute &&
                oldItem.product == newItem.product &&
                oldItem.value == newItem.value
    }
}