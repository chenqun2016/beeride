package com.bee.rider.ui.setting.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bee.rider.bean.UserBean
import com.chenchen.base.base.BaseFragment
import com.bee.rider.databinding.FragmentAreasBinding
import com.chenchen.base.utils.MMKVUtils

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
    override fun initOnce(savedInstanceState: Bundle?) {}

    override fun initViews(savedInstanceState: Bundle?) {
        val userInfo = MMKVUtils.getObject(UserBean::class.java)
        if(null != userInfo) {
            binding.tvIdcardResult.text = userInfo.city
            binding.tvIdcardHandResult.text = userInfo.buildingArea
            // TODO: 配送楼宇暂无该字段 
            binding.tvCardHealthResult.text = ""
        }
    }
}