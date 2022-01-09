package com.chenchen.bee_rider.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chenchen.bee_rider.ui.home.OrderListFragment
import com.chenchen.bee_rider.ui.order_detail.OrderDetailTab1Fragment
import com.chenchen.bee_rider.ui.order_detail.OrderDetailTab2Fragment

/**
 * 创建时间：2022/1/9
 * @Author： 陈陈陈
 * 功能描述：
 */
class OrderDetailTabAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {
    var titles = arrayOf("订单详细", "订单跟踪")

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                OrderDetailTab1Fragment()
            }
            1 -> {
                OrderDetailTab2Fragment()
            }
            else -> {
                Fragment()
            }
        }
    }
}