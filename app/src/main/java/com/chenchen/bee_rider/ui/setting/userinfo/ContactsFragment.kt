package com.chenchen.bee_rider.ui.setting.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chenchen.base.base.BaseFragment
import com.chenchen.bee_rider.databinding.FragmentContactsBinding

/**
 * 创建时间：2022/2/26
 * @Author： 陈陈陈
 * 功能描述：
 */
class ContactsFragment : BaseFragment<FragmentContactsBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentContactsBinding {
        return FragmentContactsBinding.inflate(inflater)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        binding.tvIdcardResult.text = "上海"
        binding.tvIdcardHandResult.text = "11111111111"
        binding.tvCardHealthResult.text = "朋友"
    }
}