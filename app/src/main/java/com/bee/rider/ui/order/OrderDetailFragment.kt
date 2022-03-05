package com.bee.rider.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bee.rider.Constants
import com.chenchen.base.base.BaseFragment
import com.chenchen.base.utils.LiveDataBus
import com.bee.rider.R
import com.bee.rider.databinding.FragmentOrderDetailBinding
import com.bee.rider.utils.startAlphaAnim
import com.google.android.material.tabs.TabLayout
import com.gyf.immersionbar.ImmersionBar

/**
 * 创建时间：2022/1/9
 * @Author： 陈陈陈
 * 功能描述：
 */
class OrderDetailFragment : BaseFragment<FragmentOrderDetailBinding>() {
    var fragment1:OrderDetailTab1Fragment? = null
    var fragment2:OrderDetailTab2Fragment? = null

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentOrderDetailBinding {
        return FragmentOrderDetailBinding.inflate(inflater, container, false)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
        initStatusBar()
        initTabViewpager()
        LiveDataBus.get().with("reflushToolbar").observe(this,object :Observer<Boolean>{
            override fun onChanged(t: Boolean?) {
                if(t == true){
                    binding.tabLayout.startAlphaAnim(show = true, isVisibleOrGone = true)
                    binding.ivBack.setImageResource(R.drawable.icon_back_anse)
                    binding.background.startAlphaAnim(true)
                }else{
                    binding.tabLayout.startAlphaAnim(show = false, isVisibleOrGone = true)
                    binding.ivBack.setImageResource(R.drawable.icon_back_bai)
                    binding.background.startAlphaAnim(false)
                }
            }
        })
        binding.tabLayout.alpha = 0f
    }

    private fun initStatusBar() {
        val statusBarHeight = ImmersionBar.getStatusBarHeight(this)
        val layoutParams = binding.background.layoutParams
        layoutParams.height += statusBarHeight

    }

    private fun initTabViewpager() {
        val id = arguments?.getString(Constants.ORDERID, "")
        fragment1 = OrderDetailTab1Fragment.newInstance(id)
        fragment2 = OrderDetailTab2Fragment.newInstance(id)
        val tab = binding.tabLayout.newTab()
        tab.text = "订单详细"
        val tab2 = binding.tabLayout.newTab()
        tab2.text = "订单跟踪"
        binding.tabLayout.addTab(tab)
        binding.tabLayout.addTab(tab2)
        binding.tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> {
                        switchFragment(fragment2,fragment1,"OrderDetailTab1Fragment")
                    }
                    1 -> {
                        switchFragment(fragment1,fragment2,"OrderDetailTab2Fragment")
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        switchFragment(fragment2,fragment1,"OrderDetailTab1Fragment")
    }
    fun switchFragment(from: Fragment?, to: Fragment?, tag: String?) {
        if (from == null || to == null) return
        val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
        transaction.setReorderingAllowed(true)
        if(from.isAdded){
            transaction.hide(from)
        }
        if (!to.isAdded) {
            // 隐藏当前的fragment，add下一个到Activity中
            transaction.add(R.id.fcv, to, tag).commitAllowingStateLoss()
        } else {
            // 隐藏当前的fragment，显示下一个
            // 防止onSaveInstanceState()方法之后去调用commit()出现异常，使用commitAllowingStateLoss
            transaction.show(to).commitAllowingStateLoss()
        }
    }
}