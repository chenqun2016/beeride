package com.bee.rider.ui.setting.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chenchen.base.base.BaseFragment
import com.bee.rider.databinding.FragmentCertificateBinding

/**
 * 创建时间：2022/2/26
 * @Author： 陈陈陈
 * 功能描述：
 */
class CertificateFragment : BaseFragment<FragmentCertificateBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCertificateBinding {
        return  FragmentCertificateBinding.inflate(inflater)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        val title = arguments?.getString("title")
        binding.titleView.setTitleText(title+"")
    }
}