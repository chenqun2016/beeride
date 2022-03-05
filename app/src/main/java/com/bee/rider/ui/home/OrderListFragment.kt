package com.bee.rider.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.listener.OnItemChildLongClickListener
import com.chenchen.base.base.BaseFragment
import com.chenchen.base.utils.LoadmoreUtils
import com.bee.rider.R
import com.bee.rider.bean.OrderBean
import com.bee.rider.databinding.ModelRecyclerviewBinding
import com.bee.rider.ui.adapter.HomeOrderAdapter
import com.bee.rider.utils.options

/**
 * 创建时间：2022/1/3
 * @Author： 陈陈陈
 * 功能描述：
 */
class OrderListFragment() : BaseFragment<ModelRecyclerviewBinding>() {
    companion object{
        //历史订单
        const val TYPE_HISTORY  = 999
        const val TYPE_NOMAL  = 0

        fun newInstance(type: Int): OrderListFragment {
            val args = Bundle()
            args.putInt("type",type)
            val fragment = OrderListFragment()
            fragment.arguments = args
            return fragment
        }
    }
    val adapter = HomeOrderAdapter()
    var loadmoreUtils: LoadmoreUtils<OrderBean>? = null

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ModelRecyclerviewBinding {
        return ModelRecyclerviewBinding.inflate(inflater, container, false)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        val type = arguments?.getInt("type")

        adapter.setType(type)
        adapter.setOnItemClickListener { adapter, _, position ->
            val args = Bundle()
            args.putInt("orderId", position)
            findNavController().navigate(R.id.order_detail_dest,args,options)
        }
        adapter.addChildLongClickViewIds(R.id.tv_accept)
        adapter.setOnItemChildLongClickListener (OnItemChildLongClickListener { _, view, _ ->
            if(view.id == R.id.tv_accept){
                Toast.makeText(context, "接单", Toast.LENGTH_SHORT).show()
                return@OnItemChildLongClickListener true
            }
            false
        })
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        binding.recyclerview.adapter = adapter
        loadmoreUtils = object :LoadmoreUtils<OrderBean>(adapter,binding.srl){
            override fun getDatas(page: Int) {
                val list = mutableListOf<OrderBean>()
                list.add(OrderBean())
                list.add(OrderBean())
                list.add(OrderBean())
                list.add(OrderBean())

                loadmoreUtils?.onSuccess(list)
//                loadmoreUtils?.onFail(adapter,"")
            }
        }
        loadmoreUtils?.refresh()
    }

    fun reflushDatas() {
        loadmoreUtils?.refresh()
    }

}