package com.example.my_onlinestore.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.my_onlinestore.R
import com.example.my_onlinestore.databinding.ParameterItemBinding
import com.example.my_onlinestore.databinding.ShowProductParametersBinding
import androidx.lifecycle.Observer
import com.example.my_onlinestore.viewmodels.CategoryViewModel
import com.example.my_onlinestore.viewmodels.ParameterListViewModel
import com.example.my_onlinestore.viewmodels.ParameterViewModel
import com.example.my_onlinestore.viewmodels.ProductViewModel
import com.example.my_onlinestore.views.adapters.Holder
import com.example.my_onlinestore.views.adapters.HolderBinder
import com.example.my_onlinestore.views.adapters.HolderCreator
import com.example.my_onlinestore.views.adapters.SimpleListAdapter
import com.example.my_onlinestore.views.adapters.callbacks.ParameterDiffCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ParametersFragment: Fragment() {
    private val mViewModel: ParameterListViewModel by viewModels()
    private val mArguments: ParametersFragmentArgs by navArgs()

    private lateinit var mBinding: ShowProductParametersBinding

    private val mAdapter = SimpleListAdapter(
            HolderCreator(this::createHolder),
            HolderBinder(this::bindHolder),
            ParameterDiffCallback()
    )

    private fun bindHolder(viewModel: ParameterViewModel, holder: Holder<ParameterItemBinding>){
        holder.binding.parameterViewModel = viewModel
    }

    private fun createHolder(parent: ViewGroup): Holder<ParameterItemBinding> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ParameterItemBinding.inflate(inflater, parent, false)
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
        val productViewModel: ProductViewModel = mArguments.productViewModel
        mViewModel.downloadParameters(productViewModel.id)

        mBinding = DataBindingUtil.inflate(inflater, R.layout.show_product_parameters, container, false )
        mBinding.parameterListViewModel = mViewModel
        mBinding.productViewModel = productViewModel
        mBinding.lifecycleOwner = viewLifecycleOwner
        mBinding.parameterList.adapter = mAdapter
        return mBinding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.parameterList.observe(viewLifecycleOwner, Observer {
            mAdapter.submitList(it)
        })
    }
}