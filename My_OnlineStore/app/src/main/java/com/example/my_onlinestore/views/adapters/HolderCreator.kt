package com.example.my_onlinestore.views.adapters

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding

class HolderCreator<T : ViewDataBinding>(creator: (parent: ViewGroup) -> Holder<T>) {
    private val holderCreator = creator
    fun create(parent: ViewGroup) = holderCreator(parent)
}