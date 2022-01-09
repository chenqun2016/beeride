package com.chenchen.bee_rider.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chenchen.bee_rider.ui.home.OrderListFragment

/**
 * 创建时间：2022/1/3
 * @Author： 陈陈陈
 * 功能描述：
 */
class HomeAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    var titles = arrayOf("新订单", "待取货", "待送达")

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun createFragment(position: Int): Fragment {
        return OrderListFragment.newInstance(position)
    }
}