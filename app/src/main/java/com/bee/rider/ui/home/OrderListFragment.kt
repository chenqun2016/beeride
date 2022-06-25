package com.bee.rider.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bee.rider.Constants
import com.chad.library.adapter.base.listener.OnItemChildLongClickListener
import com.chenchen.base.base.BaseFragment
import com.chenchen.base.utils.LoadmoreUtils
import com.bee.rider.R
import com.bee.rider.bean.OrderListBean
import com.bee.rider.databinding.ModelRecyclerviewBinding
import com.bee.rider.http.NetworkApi
import com.bee.rider.params.InitiativeCreateParams
import com.bee.rider.params.OrderListParams
import com.bee.rider.params.QueryVO
import com.bee.rider.ui.adapter.HomeOrderAdapter
import com.bee.rider.utils.options
import com.bee.rider.vm.HomeViewModel
import com.chenchen.base.constants.HttpConstants
import com.chenchen.base.utils.MMKVUtils
import kotlinx.coroutines.launch
import java.util.*

/**
 * 创建时间：2022/1/3
 * @Author： 陈陈陈
 * 功能描述：
 */
class OrderListFragment() : BaseFragment<ModelRecyclerviewBinding>() {
    companion object {
        //历史订单
        const val TYPE_HISTORY = 999

        fun newInstance(type: Int): OrderListFragment {
            val args = Bundle()
            args.putInt("type", type)
            val fragment = OrderListFragment()
            fragment.arguments = args
            return fragment
        }
    }
    private val viewModel: HomeViewModel by viewModels()
    val adapter = HomeOrderAdapter()
    var loadmoreUtils: LoadmoreUtils<OrderListBean.RecordsBean>? = null
    var beginDate: Date? = null
    var endDate:Date? = null

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ModelRecyclerviewBinding {
        return ModelRecyclerviewBinding.inflate(inflater, container, false)
    }

    override fun initOnce(savedInstanceState: Bundle?) {
        viewModel.homeList.observe(this, {
            if (it.isSuccess) {
                val bean = it.getOrNull()
                if (null != bean) {
                    loadmoreUtils?.onSuccess(bean.records)
                }else{
                    loadmoreUtils?.onSuccess(listOf())
                }
            } else {
                loadmoreUtils?.onFail(it.exceptionOrNull()?.message)
            }
        })

        viewModel.historyList.observe(this, {
            if (it.isSuccess) {
                val bean = it.getOrNull()
                if (null != bean) {
                    loadmoreUtils?.onSuccess(bean.records)
                }else{
                    loadmoreUtils?.onSuccess(listOf())
                }
            } else {
                loadmoreUtils?.onFail(it.exceptionOrNull()?.message)
            }
        })
    }
    override fun initViews(savedInstanceState: Bundle?) {
        val type = arguments?.getInt("type")
        adapter.setType(type)
        adapter.setOnItemClickListener { a, _, position ->
            val args = Bundle()
            args.putString(Constants.TAKEOUTID, adapter.data[position].takeoutId.toString())
            args.putString(Constants.ORDERID, adapter.data[position].orderId.toString())
            findNavController().navigate(R.id.order_detail_dest, args, options)
        }
        adapter.addChildLongClickViewIds(R.id.tv_accept)
        adapter.setOnItemChildLongClickListener(OnItemChildLongClickListener { _, view, position ->
            if (view.id == R.id.tv_accept) {
                val recordsBean = adapter.data[position]

                val param = InitiativeCreateParams(recordsBean.takeoutId,recordsBean.takeoutId)
                viewModel.viewModelScope.launch {
                    val it = NetworkApi.initiativeCreate(param)
                    if (it.isSuccess) {
                        val bean = it.getOrNull()
                        Toast.makeText(context, "接单成功", Toast.LENGTH_SHORT).show()
                        adapter.removeAt(position)
                    } else {
                        loadmoreUtils?.onFail(it.exceptionOrNull()?.message)
                    }
                }
                return@OnItemChildLongClickListener true
            }
            false
        })
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        binding.recyclerview.adapter = adapter
        loadmoreUtils = object : LoadmoreUtils<OrderListBean.RecordsBean>(adapter, binding.srl) {
            override fun getDatas(page: Int) {
                if(type == TYPE_HISTORY){
                    val beginDate1 = Constants.sdfLong1.format(beginDate)
                    val endDate1 = Constants.sdfLong1.format(endDate)
                    val params = OrderListParams(QueryVO(0,beginDate1,endDate1), page, PAGE_SIZE)
                    viewModel.doHistoryList(params)
                }else{
                    var status: Int = 10
                    when (type) {
                        0 -> status = 10
                        1 -> status = 20
                        2 -> status = 30
                    }
                    val params = OrderListParams(QueryVO(status), page, PAGE_SIZE)
                    viewModel.doHomeList(params)
                }
            }
        }
        loadmoreUtils?.refresh()
    }
    fun reflushDatas(beginDate :Date?,endDate:Date?) {
        this.beginDate = beginDate
        this.endDate = endDate
        loadmoreUtils?.refresh()
    }
}