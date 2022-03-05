package com.bee.rider.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chenchen.base.base.BaseFragment
import com.bee.rider.R
import com.bee.rider.databinding.FragmentAboutBinding
import com.bee.rider.databinding.FragmentSystemSettingBinding
import com.gyf.immersionbar.ImmersionBar
/**
 - @Description: 关于趣鲜蜂
 - @Author: bxy
 - @Time:  2022/1/16 下午5:52
 */
class AboutFragment : BaseFragment<FragmentAboutBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentAboutBinding {
        return FragmentAboutBinding.inflate(inflater, container, false)
    }

    override fun initViews(savedInstanceState: Bundle?) {
    }
}