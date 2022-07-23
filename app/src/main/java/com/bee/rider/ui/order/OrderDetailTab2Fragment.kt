package com.bee.rider.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bee.rider.Constants
import com.bee.rider.bean.OrderDetailBean
import com.chenchen.base.base.BaseFragment
import com.chenchen.base.utils.DisplayUtil
import com.bee.rider.databinding.FragmentOrderDetailTab2Binding
import com.bee.rider.ui.adapter.OrderDetailTraceAdapter
import com.bee.rider.utils.UIUtils
import com.bee.rider.vm.OrderDetailViewModel
import com.gyf.immersionbar.ImmersionBar

/**
 * 创建时间：2022/1/9
 * @Author： 陈陈陈
 * 功能描述：
 */
class OrderDetailTab2Fragment :BaseFragment<FragmentOrderDetailTab2Binding>() {
    private val viewModel: OrderDetailViewModel by viewModels()
    val orderDetailTraceAdapter = OrderDetailTraceAdapter()

    companion object{
        fun newInstance(id: String?): OrderDetailTab2Fragment{
            val args = Bundle()
            args.putString(Constants.ORDERID, id)
            val fragment = OrderDetailTab2Fragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentOrderDetailTab2Binding {
       return FragmentOrderDetailTab2Binding.inflate(inflater, container, false)
    }
    override fun initOnce(savedInstanceState: Bundle?) {
        viewModel.getOperateHistory.observe(this,{
            if (it.isSuccess){
                val mData = it.getOrNull()
                if(null != mData && mData.trackList.isNotEmpty()){
                    orderDetailTraceAdapter.setNewInstance(mData.trackList.toMutableList())
                }
            }
        })
    }

    override fun initViews(savedInstanceState: Bundle?) {
        val layoutParams = binding.clContent.layoutParams as FrameLayout.LayoutParams
        layoutParams.topMargin = DisplayUtil.dip2px(context,50f)+ImmersionBar.getStatusBarHeight(this)

        binding.recyclerview.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        binding.recyclerview.adapter = orderDetailTraceAdapter

//        val datas = mutableListOf("1提交订单","21提交订单","31提交订单","41提交订单","51提交订单")
//        orderDetailTraceAdapter.setNewInstance(datas)

        getDatas()
        bindDatas()
    }

    private fun getDatas() {

        val id = arguments?.getString(Constants.ORDERID)
        viewModel.doGetOperateHistory(id)
    }

    var mDatas :OrderDetailBean? = null
    fun setDatas(item: OrderDetailBean) {
        mDatas = item
       bindDatas()
    }

    private fun bindDatas() {
        if(null != mDatas && isBindingViewCreated()){
            binding.tvTime.text = "${UIUtils.getNomalTime2(mDatas?.disTakeout?.expectedTime)}前送达"
            binding.tvTime2.text = "(系统派送)"
            binding.tvRight.text = "#${mDatas?.deliverySn}"
        }
    }
}