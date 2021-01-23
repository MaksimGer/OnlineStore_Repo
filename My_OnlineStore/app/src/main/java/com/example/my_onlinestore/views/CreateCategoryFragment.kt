package com.example.my_onlinestore.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.my_onlinestore.R
import com.example.my_onlinestore.databinding.AddAttributeItemBinding
import com.example.my_onlinestore.databinding.CreateCategoryLayoutBinding
import com.example.my_onlinestore.model.Category
import com.example.my_onlinestore.viewmodels.AddedAttributeViewModel
import com.example.my_onlinestore.viewmodels.CategoryViewModel
import com.example.my_onlinestore.viewmodels.SaveCategoryViewModel
import com.example.my_onlinestore.views.adapters.Holder
import com.example.my_onlinestore.views.adapters.HolderBinder
import com.example.my_onlinestore.views.adapters.HolderCreator
import com.example.my_onlinestore.views.adapters.SimpleListAdapter
import com.example.my_onlinestore.views.adapters.callbacks.AddedAttributeDiffCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateCategoryFragment: Fragment() {
    private val mViewModel: SaveCategoryViewModel by viewModels()

    private lateinit var mBinding: CreateCategoryLayoutBinding
    private lateinit var newCategory: Category

    private val mAdapter = SimpleListAdapter(
            HolderCreator(this::createHolder),
            HolderBinder(this::bindHolder),
            AddedAttributeDiffCallback()
    )

    private fun bindHolder(viewModel: AddedAttributeViewModel, holder: Holder<AddAttributeItemBinding>){
        holder.binding.attributeViewModel = viewModel
    }

    private fun createHolder(parent: ViewGroup): Holder<AddAttributeItemBinding> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AddAttributeItemBinding.inflate(inflater, parent, false)
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.create_category_layout, container, false)
        mBinding.categoryViewModel = mViewModel
        mBinding.lifecycleOwner = viewLifecycleOwner
        mBinding.attributeList.adapter = mAdapter
        mBinding.btnCreate.setOnClickListener {
            mViewModel.onSave()
        }
        return mBinding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.attributes.observe(viewLifecycleOwner, Observer {
            mAdapter.submitList(it)
        })

        mViewModel.onCategorySaved.observe(viewLifecycleOwner, Observer {
            if(it){
                findNavController().navigate(CreateCategoryFragmentDirections.actionCreateCategoryFragmentToCategoriesFragment())
            }
            else{
                Toast.makeText(requireContext(), "ERROR", Toast.LENGTH_SHORT).show()
            }
        })
    }
}