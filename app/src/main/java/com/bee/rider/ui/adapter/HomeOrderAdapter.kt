package com.bee.rider.ui.adapter

import android.view.View
import androidx.constraintlayout.widget.Group
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.bee.rider.R
import com.bee.rider.bean.OrderBean
import com.bee.rider.bean.OrderListBean
import com.bee.rider.ui.home.OrderListFragment.Companion.TYPE_HISTORY
import com.bee.rider.ui.home.OrderListFragment.Companion.TYPE_NOMAL

/**
 * 创建时间：2022/1/3
 * @Author： 陈陈陈
 * 功能描述：
 */
class HomeOrderAdapter() : BaseQuickAdapter<OrderListBean.RecordsBean, BaseViewHolder>(R.layout.item_home_order),LoadMoreModule{
    override fun convert(holder: BaseViewHolder, item: OrderListBean.RecordsBean) {
        when(mType){
            TYPE_HISTORY ->{
                holder.getView<Group>(R.id.id_order_history_gone).visibility = View.GONE
                holder.getView<Group>(R.id.id_order_detail_gone).visibility = View.GONE
            }
            TYPE_NOMAL ->{
                holder.getView<Group>(R.id.id_order_history_gone).visibility = View.VISIBLE
                holder.getView<Group>(R.id.id_order_detail_gone).visibility = View.VISIBLE
            }
        }
    }

    var mType :Int? = TYPE_NOMAL
    fun setType(type: Int?) {
        mType = type

    }
}