package com.chenchen.bee_rider.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chenchen.base.base.BaseFragment
import com.chenchen.base.utils.MMKVUtils
import com.chenchen.bee_rider.Constants
import com.chenchen.bee_rider.databinding.FragmentNoticeSettingBinding

/**
 * 创建时间：2022/2/20
 * @Author： 陈陈陈
 * 功能描述：
 */
class NoticeSettingFragment : BaseFragment<FragmentNoticeSettingBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentNoticeSettingBinding {
        return FragmentNoticeSettingBinding.inflate(inflater)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        binding.tvNoticeVoice.text = "播放提示音"
        binding.tvNoticeZhengdong.text = "震动"

        binding.sbNoticeVoice.setOnCheckedChangeListener { view, isChecked ->
            MMKVUtils.putBoolean(Constants.SETTING_NOTICE_VOICE,isChecked)
        }
        binding.sbNoticeZhengdong.setOnCheckedChangeListener { view, isChecked ->
            MMKVUtils.putBoolean(Constants.SETTING_NOTICE_SHAKE,isChecked)
        }
        val shake = MMKVUtils.getBoolean(Constants.SETTING_NOTICE_SHAKE, true)
        val voice = MMKVUtils.getBoolean(Constants.SETTING_NOTICE_VOICE, true)
        binding.sbNoticeVoice.isChecked = voice
        binding.sbNoticeZhengdong.isChecked = shake
    }
}