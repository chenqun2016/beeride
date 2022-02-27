package com.chenchen.bee_rider.ui.setting.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chenchen.base.base.BaseFragment
import com.chenchen.bee_rider.databinding.FragmentAreasBinding

/**
 * 创建时间：2022/2/26
 * @Author： 陈陈陈
 * 功能描述：
 */
class AreasFragment : BaseFragment<FragmentAreasBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentAreasBinding {
        return FragmentAreasBinding.inflate(inflater)
    }

    override fun initViews(savedInstanceState: Bundle?) {

        binding.tvIdcardResult.text = "上海市 / 上海市 / 青浦区"
        binding.tvIdcardHandResult.text = "陆家嘴"
        binding.tvCardHealthResult.text = "陆家嘴"
    }
}