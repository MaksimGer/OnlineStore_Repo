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
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.my_onlinestore.R
import com.example.my_onlinestore.databinding.CategoryItemBinding
import com.example.my_onlinestore.databinding.CategoryListLayoutBinding
import com.example.my_onlinestore.viewmodels.CategoryListViewModel
import com.example.my_onlinestore.viewmodels.CategoryViewModel
import com.example.my_onlinestore.views.adapters.Holder
import com.example.my_onlinestore.views.adapters.HolderBinder
import com.example.my_onlinestore.views.adapters.HolderCreator
import com.example.my_onlinestore.views.adapters.SimpleListAdapter
import com.example.my_onlinestore.views.adapters.callbacks.CategoryDiffCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.category_list_layout.view.*

@AndroidEntryPoint
class CategoryFragment: Fragment() {
    private val mViewModel: CategoryListViewModel by viewModels()

    private lateinit var mBinding: CategoryListLayoutBinding

    private val mAdapter = SimpleListAdapter(
            HolderCreator(this::createHolder),
            HolderBinder(this::bindHolder),
            CategoryDiffCallback()
    )

    private fun bindHolder(viewModel: CategoryViewModel, holder: Holder<CategoryItemBinding>){
        holder.binding.categoryViewModel = viewModel
        holder.binding.categoryListViewModel = mViewModel
        holder.binding.categoryName.setOnClickListener {
            val extras = FragmentNavigatorExtras(
                it to "categoryName"
            )
            findNavController().navigate(CategoryFragmentDirections.actionCategoriesFragmentToProductsFragment(viewModel), extras)
        }
    }

    private fun createHolder(parent: ViewGroup): Holder<CategoryItemBinding> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryItemBinding.inflate(inflater, parent, false)
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.category_list_layout, container, false)
        mBinding.categoryViewModel = mViewModel
        mBinding.lifecycleOwner = viewLifecycleOwner
        mBinding.categoryList.adapter = mAdapter
        mBinding.btnCreateCategory.setOnClickListener {
            findNavController().navigate(CategoryFragmentDirections.actionCategoriesFragmentToCreateCategoryFragment())
        }
        return mBinding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.categoryList.observe(viewLifecycleOwner, Observer {
            mAdapter.submitList(it)
        })
    }
}