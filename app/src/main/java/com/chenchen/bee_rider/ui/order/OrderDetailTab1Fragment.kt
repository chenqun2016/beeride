package com.chenchen.bee_rider.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chenchen.base.base.BaseFragment
import com.chenchen.bee_rider.databinding.FragmentOrderDetailTab1Binding
import com.chenchen.bee_rider.ui.adapter.ProductsAdapter

/**
 * 创建时间：2022/1/9
 * @Author： 陈陈陈
 * 功能描述：
 */
class OrderDetailTab1Fragment : BaseFragment<FragmentOrderDetailTab1Binding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentOrderDetailTab1Binding {
        return FragmentOrderDetailTab1Binding.inflate(inflater, container, false)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        binding.includeProducts.products.layoutManager = LinearLayoutManager(context)
        val productsAdapter = ProductsAdapter()
        binding.includeProducts.products.adapter = productsAdapter
        val products = mutableListOf<String>("1","2","3")
        productsAdapter.setNewInstance(products)
    }

}