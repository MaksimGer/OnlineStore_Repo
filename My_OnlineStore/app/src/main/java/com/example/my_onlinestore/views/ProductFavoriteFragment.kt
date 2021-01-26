package com.example.my_onlinestore.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.my_onlinestore.R
import com.example.my_onlinestore.databinding.FavoriteProductItemBinding
import com.example.my_onlinestore.databinding.ShowFavoriteProductsBinding
import com.example.my_onlinestore.viewmodels.FavoriteProductListViewModel
import com.example.my_onlinestore.viewmodels.ProductViewModel
import com.example.my_onlinestore.views.adapters.Holder
import com.example.my_onlinestore.views.adapters.HolderBinder
import com.example.my_onlinestore.views.adapters.HolderCreator
import com.example.my_onlinestore.views.adapters.SimpleListAdapter
import com.example.my_onlinestore.views.adapters.callbacks.ProductDiffCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFavoriteFragment: Fragment (){
    private val mViewModel: FavoriteProductListViewModel by viewModels()

    private lateinit var mBinding: ShowFavoriteProductsBinding

    private val mAdapter = SimpleListAdapter(
        HolderCreator(this::createHolder),
        HolderBinder(this::bindHolder),
        ProductDiffCallback()
    )

    private fun bindHolder(viewModel: ProductViewModel, holder: Holder<FavoriteProductItemBinding>){
        holder.binding.productViewModel = viewModel
        holder.binding.productName.setOnClickListener {
            mViewModel.unLikeProduct(viewModel)
        }
    }

    private fun createHolder(parent: ViewGroup): Holder<FavoriteProductItemBinding> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FavoriteProductItemBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.show_favorite_products, container, false )
        mBinding.productListViewModel = mViewModel
        mBinding.lifecycleOwner = viewLifecycleOwner
        mBinding.productList.adapter = mAdapter
        return mBinding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.productList.observe(viewLifecycleOwner, Observer {
            mAdapter.submitList(it)
        })
    }
}