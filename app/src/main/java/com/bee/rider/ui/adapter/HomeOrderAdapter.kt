package com.bee.rider.ui.adapter

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.Group
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.bee.rider.R
import com.bee.rider.bean.OrderListBean
import com.bee.rider.ui.home.OrderListFragment.Companion.TYPE_HISTORY
import com.bee.rider.utils.UIUtils

/**
 * 创建时间：2022/1/3
 * @Author： 陈陈陈
 * 功能描述：
 */
class HomeOrderAdapter() : BaseQuickAdapter<OrderListBean.RecordsBean, BaseViewHolder>(R.layout.item_home_order),LoadMoreModule{
    override fun convert(holder: BaseViewHolder, item: OrderListBean.RecordsBean) {
        val accept = holder.getView<AppCompatTextView>(R.id.tv_accept)
        when(mType){
            TYPE_HISTORY ->{
                accept.visibility = View.GONE
                holder.getView<Group>(R.id.id_order_history_gone).visibility = View.GONE
                holder.getView<Group>(R.id.id_order_detail_gone).visibility = View.GONE
            }
            else ->{
                accept.visibility = View.VISIBLE
                holder.getView<Group>(R.id.id_order_history_gone).visibility = View.VISIBLE
                holder.getView<Group>(R.id.id_order_detail_gone).visibility = View.VISIBLE
            }
        }
        UIUtils.setAccepeButtomTextByType(mType,accept)

        holder.setText(R.id.tv_right,"#${item.deliverySn}")
        holder.setText(R.id.tv_store_name, item.storeName)
        holder.setText(R.id.tv_store_address, item.storeAddressDetail)
        holder.setText(R.id.tv_customer_address, item.detailAddress)
        holder.setText(R.id.tv_customer_name_phone,"${item.linkman}/${item.phone}")
        holder.setText(R.id.tv_store_name, item.storeName)
        //TODO
        holder.setText(R.id.tv_time,"${UIUtils.getNomalTime2(item.expectedTime)}前送达")
        holder.setText(R.id.tv_time_2,"(系统派送)")
        holder.setText(R.id.tv_store_distance,"距离1.5km")
        holder.setText(R.id.tv_customer_distance, "距离1.5km")
    }

    var mType :Int = 0
    fun setType(type: Int) {
        mType = type
    }
}