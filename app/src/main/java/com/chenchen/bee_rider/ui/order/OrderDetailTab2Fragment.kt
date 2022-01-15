package com.chenchen.bee_rider.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chenchen.base.base.BaseFragment
import com.chenchen.bee_rider.databinding.FragmentOrderDetailTab2Binding
import com.chenchen.bee_rider.ui.adapter.OrderDetailTraceAdapter

/**
 * 创建时间：2022/1/9
 * @Author： 陈陈陈
 * 功能描述：
 */
class OrderDetailTab2Fragment :BaseFragment<FragmentOrderDetailTab2Binding>() {

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentOrderDetailTab2Binding {
       return FragmentOrderDetailTab2Binding.inflate(inflater, container, false)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        binding.recyclerview.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,true)
        val orderDetailTraceAdapter = OrderDetailTraceAdapter()
        binding.recyclerview.adapter = orderDetailTraceAdapter

        val datas = mutableListOf("1提交订单","21提交订单","31提交订单","41提交订单","51提交订单")
        orderDetailTraceAdapter.setNewInstance(datas)
    }

}