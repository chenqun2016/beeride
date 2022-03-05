package com.bee.rider.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.view.marginTop
import com.chenchen.base.base.BaseFragment
import com.chenchen.base.utils.d
import com.bee.rider.R
import com.bee.rider.databinding.FragmentCommentsBinding
import com.bee.rider.ui.adapter.CommentsAdapter
import com.bee.rider.utils.UIUtils
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * 创建时间：2022/2/4
 * @Author： 陈陈陈
 * 功能描述：
 */
class CommentsFragment : BaseFragment<FragmentCommentsBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCommentsBinding {
        return FragmentCommentsBinding.inflate(inflater)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        UIUtils.setGradientDrawable(binding.llTitleContent,R.color.color_FE8102, R.color.color_FF6200)
        binding.llTitleContent.post {
            binding.llTitleContent.setPadding(0,binding.titleView.height,0,0)
        }

        val titles = listOf("全部","非常差(78)","一般(122)","超赞(1111)")
        binding.viewpager.adapter = CommentsAdapter(titles,this)
        TabLayoutMediator(
            binding.tabLayout, binding.viewpager
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = titles[position]
        }.attach()
    }
}