package com.bee.rider.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chenchen.base.base.BaseFragment
import com.chenchen.base.utils.MMKVUtils
import com.bee.rider.Constants
import com.bee.rider.databinding.FragmentNoticeSettingBinding

/**
 * 创建时间：2022/2/20
 * @Author： 陈陈陈
 * 功能描述：
 */
class OrderSettingFragment  : BaseFragment<FragmentNoticeSettingBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentNoticeSettingBinding {
        return FragmentNoticeSettingBinding.inflate(inflater)
    }
    override fun initOnce(savedInstanceState: Bundle?) {}

    override fun initViews(savedInstanceState: Bundle?) {
        binding.titleView.setTitleText("订单设置")
        binding.tvNoticeVoice.text = "自动接单"
        binding.tvNoticeZhengdong.text = "一键接单"

        binding.sbNoticeVoice.setOnCheckedChangeListener { view, isChecked ->
            MMKVUtils.putBoolean(Constants.SETTING_ORDER_ACCEPT_AUTO,isChecked)
        }
        binding.sbNoticeZhengdong.setOnCheckedChangeListener { view, isChecked ->
            MMKVUtils.putBoolean(Constants.SETTING_ORDER_ACCEPT_ONEKEY,isChecked)
        }
        val voice = MMKVUtils.getBoolean(Constants.SETTING_ORDER_ACCEPT_AUTO, true)
        val shake = MMKVUtils.getBoolean(Constants.SETTING_ORDER_ACCEPT_ONEKEY, true)

        binding.sbNoticeVoice.isChecked = voice
        binding.sbNoticeZhengdong.isChecked = shake
    }
}