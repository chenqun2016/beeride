package com.bee.rider.ui.setting

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
import com.bee.rider.R
import com.bee.rider.databinding.FragmentPersonalBinding
import com.bee.rider.utils.UIUtils
import com.bee.rider.utils.ViewHelper
import com.bee.rider.utils.options
import com.bee.rider.utils.setRoundConner
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
        UIUtils.setGradientDrawable(this,null,binding.ivBg,R.color.color_FF6200)
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
        binding.tvDatas.setOnClickListener(this)
        binding.tvComments.setOnClickListener(this)
        binding.tvPersional.setOnClickListener(this)
        binding.tvMessage.setOnClickListener(this)
        binding.tvSystemSetting.setOnClickListener(this)

        binding.switchButton.setOnCheckedChangeListener { view, isChecked ->
            Toast.makeText(context, "$isChecked", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_history -> {
                findNavController().navigate(R.id.next_action_history,null, options)
            }
            R.id.tv_datas -> {

            }
            R.id.tv_comments -> {
                findNavController().navigate(R.id.next_action_comment,null, options)
            }
            R.id.tv_persional -> {
                findNavController().navigate(R.id.userinfo_dest,null, options)
            }
            R.id.tv_message -> {

            }
            R.id.tv_system_setting -> {
                findNavController().navigate(R.id.system_set,null, options)
            }
        }
    }
}