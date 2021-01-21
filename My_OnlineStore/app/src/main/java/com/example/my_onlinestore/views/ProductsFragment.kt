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
import androidx.navigation.fragment.navArgs
import com.example.my_onlinestore.R
import com.example.my_onlinestore.databinding.ProductItemBinding
import com.example.my_onlinestore.databinding.ShowCategoryProductsBinding
import com.example.my_onlinestore.viewmodels.CategoryViewModel
import com.example.my_onlinestore.viewmodels.ProductListViewModel
import com.example.my_onlinestore.viewmodels.ProductViewModel
import com.example.my_onlinestore.views.adapters.Holder
import com.example.my_onlinestore.views.adapters.HolderBinder
import com.example.my_onlinestore.views.adapters.HolderCreator
import com.example.my_onlinestore.views.adapters.SimpleListAdapter
import com.example.my_onlinestore.views.adapters.callbacks.ProductDiffCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment: Fragment() {
    private val mViewModel: ProductListViewModel by viewModels()
    private val mArguments: ProductsFragmentArgs by navArgs()

    private lateinit var mBinding: ShowCategoryProductsBinding

    private val mAdapter = SimpleListAdapter(
    HolderCreator(this::createHolder),
    HolderBinder(this::bindHolder),
    ProductDiffCallback()
    )

    private fun bindHolder(viewModel: ProductViewModel, holder: Holder<ProductItemBinding>){
        holder.binding.productViewModel = viewModel
    }

    private fun createHolder(parent: ViewGroup): Holder<ProductItemBinding> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
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
        val categoryViewModel: CategoryViewModel = mArguments.categoryViewModel
        mViewModel.downloadProducts(categoryViewModel.id.toLong())

        mBinding = DataBindingUtil.inflate(inflater, R.layout.show_category_products, container, false )
        mBinding.productListViewModel = mViewModel
        mBinding.categoryViewModel = categoryViewModel
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