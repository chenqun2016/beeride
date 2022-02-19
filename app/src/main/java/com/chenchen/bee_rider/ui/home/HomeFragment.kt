package com.chenchen.bee_rider.ui.home

import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.chenchen.base.base.BaseFragment
import com.chenchen.base.utils.DisplayUtil
import com.chenchen.bee_rider.R
import com.chenchen.bee_rider.databinding.FragmentHome2Binding
import com.chenchen.bee_rider.ui.adapter.HomeAdapter
import com.chenchen.bee_rider.utils.UIUtils
import com.chenchen.bee_rider.utils.options
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.gyf.immersionbar.ImmersionBar

/**
 * 创建时间：2022/1/3
 * @Author： 陈陈陈
 * 功能描述：
 */
class HomeFragment : BaseFragment<FragmentHome2Binding>(), View.OnClickListener {

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHome2Binding {
        return FragmentHome2Binding.inflate(inflater, container, false)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        UIUtils.setGradientDrawable(this,binding.statusBar,binding.ivBg,R.color.color_FF6200)

        binding.ivIcon.setOnClickListener(this)
        binding.tvName.setOnClickListener(this)
        binding.ivIconWork.setOnClickListener(this)
        binding.tvWork.setOnClickListener(this)
        binding.ivRight.setOnClickListener(this)
        binding.ivSetting.setOnClickListener(this)

        val adapter = HomeAdapter(this)
        binding.viewpager.adapter = adapter
        TabLayoutMediator(
            binding.tabLayout, binding.viewpager
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = adapter.titles[position]
            val textView = TextView(context)
            textView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            textView.text = adapter.titles[position]
            textView.setTextColor(resources.getColor(R.color.white))
            textView.textSize = 18f
            textView.gravity = Gravity.CENTER
            textView.typeface = if(position==0)Typeface.DEFAULT_BOLD else Typeface.DEFAULT
            tab.customView = textView
        }.attach()
        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val textView = tab?.customView as TextView
                textView.typeface = Typeface.DEFAULT_BOLD
                tab.customView = textView
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val textView = tab?.customView as TextView
                textView.typeface = Typeface.DEFAULT
                tab.customView = textView
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        binding.tabLayout.post {
            binding.tabLayout.getTabAt(0)?.select()
        }

        val toolbarHeight = DisplayUtil.dip2px(context,64f)
        val diff = DisplayUtil.dip2px(context,246f) - toolbarHeight
        binding.appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val rate = verticalOffset.toFloat() / appBarLayout.totalScrollRange
            binding.ivBg.translationY = rate * diff
        })
        binding.collapsing.setContentScrimColor(resources.getColor(R.color.color_FF6600))
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_work,
            R.id.iv_icon_work,
            R.id.tv_name,
            R.id.iv_icon -> {
                findNavController().navigate(R.id.next_action_persional,null, options)
            }
            R.id.iv_setting -> {
                findNavController().navigate(R.id.next_action_system_set,null, options)
            }
            R.id.iv_right -> {
            }
        }
    }


}