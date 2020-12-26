package com.example.my_onlinestore.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.my_onlinestore.R
import com.example.my_onlinestore.databinding.ActivityMainBinding
import com.example.my_onlinestore.databinding.CategoryItemBinding
import com.example.my_onlinestore.viewmodels.CategoryListViewModel
import com.example.my_onlinestore.viewmodels.CategoryViewModel
import com.example.my_onlinestore.views.adapters.*
import com.example.my_onlinestore.views.adapters.callbacks.CayegoryDiffCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mCategoryListViewModel: CategoryListViewModel by viewModels()
    private lateinit var mBinding: ActivityMainBinding

    private val mAdapter = SimpleListAdapter(
        HolderCreator(this::createHolder),
        HolderBinder(this::bindHolder),
        CayegoryDiffCallback()
    )

    private fun bindHolder(viewModel: CategoryViewModel, holder: Holder<CategoryItemBinding>){
        holder.binding.categoryViewModel = viewModel
    }

    private fun createHolder(parent: ViewGroup): Holder<CategoryItemBinding> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryItemBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.categoryList.adapter = mAdapter
        mCategoryListViewModel.categoryList.observe(this, Observer { categoryList ->
            mAdapter.submitList(categoryList)
        })
    }
}