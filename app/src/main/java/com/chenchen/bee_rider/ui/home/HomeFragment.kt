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
import com.chenchen.bee_rider.R
import com.chenchen.bee_rider.databinding.FragmentHomeBinding
import com.chenchen.bee_rider.ui.adapter.HomeAdapter
import com.chenchen.bee_rider.utils.UIUtils
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.gyf.immersionbar.ImmersionBar

/**
 * 创建时间：2022/1/3
 * @Author： 陈陈陈
 * 功能描述：
 */
class HomeFragment : BaseFragment() {
    private var _binding:FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initImmersionBar()
        initViews()
    }

    private fun initImmersionBar() {
        //在BaseActivity里初始化
        val mImmersionBar = ImmersionBar.with(this)
        mImmersionBar.statusBarDarkFont(true, 0.2f)
        mImmersionBar.init()
    }

    private fun initViews() {
        UIUtils.setGradientDrawable(this,binding.statusBar,binding.ivBg,R.color.color_FF6200)

        binding.ivRight.setOnClickListener{
            findNavController().navigate(R.id.next_action_history)
        }

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
    }

}