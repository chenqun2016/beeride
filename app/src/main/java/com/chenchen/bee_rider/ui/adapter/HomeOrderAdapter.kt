package com.chenchen.bee_rider.ui.adapter

import android.view.View
import androidx.constraintlayout.widget.Group
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.chenchen.bee_rider.R
import com.chenchen.bee_rider.bean.OrderBean
import com.chenchen.bee_rider.ui.home.OrderListFragment.Companion.TYPE_HISTORY
import com.chenchen.bee_rider.ui.home.OrderListFragment.Companion.TYPE_NOMAL

/**
 * 创建时间：2022/1/3
 * @Author： 陈陈陈
 * 功能描述：
 */
class HomeOrderAdapter() : BaseQuickAdapter<OrderBean, BaseViewHolder>(R.layout.item_home_order),LoadMoreModule{
    override fun convert(holder: BaseViewHolder, item: OrderBean) {
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