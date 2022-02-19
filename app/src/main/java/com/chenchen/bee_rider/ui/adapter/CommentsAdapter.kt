package com.chenchen.bee_rider.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chenchen.bee_rider.ui.setting.CommentsListFragment

/**
 * 创建时间：2022/2/4
 * @Author： 陈陈陈
 * 功能描述：
 */
class CommentsAdapter (private val titles:List<String>, fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return titles.size
    }

    override fun createFragment(position: Int): Fragment {
        return CommentsListFragment.newInstance(position)
    }
}