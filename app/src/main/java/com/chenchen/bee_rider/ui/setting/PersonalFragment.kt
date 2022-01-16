package com.chenchen.bee_rider.ui.setting

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.chenchen.base.base.BaseFragment
import com.chenchen.base.utils.DisplayUtil
import com.chenchen.bee_rider.R
import com.chenchen.bee_rider.databinding.FragmentPersonalBinding
import com.chenchen.bee_rider.utils.ViewHelper
import com.chenchen.bee_rider.utils.options
import com.chenchen.bee_rider.utils.setRoundConner
import com.gyf.immersionbar.ImmersionBar
import kotlin.math.roundToInt

/**
 * 创建时间：2022/1/15
 * @Author： 陈陈陈
 * 功能描述：
 */
class PersonalFragment : BaseFragment<FragmentPersonalBinding>(), View.OnClickListener {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentPersonalBinding {
        return FragmentPersonalBinding.inflate(inflater,container,false)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        initImmersionBar()
        binding.scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, totalScrollY, oldScrollX, oldScrollY ->
            if(totalScrollY >= 100){
                binding.titleView.setBackgroundAlpha(255)
            }else{
                binding.titleView.setBackgroundAlpha(((totalScrollY / 100f)* 255).roundToInt())
            }
            if(totalScrollY >= 50){
                binding.titleView.setContentColor(1)
            }else{
                binding.titleView.setContentColor(0)
            }
        }
        )
        binding.clPersional.setRoundConner(DisplayUtil.dip2px(context,10f),ViewHelper.RADIUS_TOP)
        binding.clData.setRoundConner(DisplayUtil.dip2px(context,10f),ViewHelper.RADIUS_BOTTOM)
        binding.tvHistory.setOnClickListener(this)
        binding.tvSystemSetting.setOnClickListener(this)
        binding.switchButton.setOnCheckedChangeListener { view, isChecked ->
            Toast.makeText(context, "$isChecked", Toast.LENGTH_SHORT).show()
        }
    }
    private fun initImmersionBar() {
        val mImmersionBar = ImmersionBar.with(this)
        mImmersionBar.statusBarDarkFont(false, 0.2f)
        mImmersionBar.init()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_history -> {
                findNavController().navigate(R.id.next_action_history,null, options)
            }
            R.id.tv_datas -> {

            }
            R.id.tv_comments -> {

            }
            R.id.tv_persional -> {

            }
            R.id.tv_message -> {

            }
            R.id.tv_system_setting -> {
                findNavController().navigate(R.id.system_set,null, options)
            }
        }
    }
}