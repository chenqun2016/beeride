package com.bee.rider.ui.setting.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chenchen.base.base.BaseFragment
import com.bee.rider.R
import com.bee.rider.databinding.FragmentCertificatesBinding
import com.bee.rider.utils.options

/**
 * 创建时间：2022/2/26
 * @Author： 陈陈陈
 * 功能描述：
 */
class CertificatesFragment : BaseFragment<FragmentCertificatesBinding>(), View.OnClickListener {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCertificatesBinding {
        return  FragmentCertificatesBinding.inflate(inflater)
    }
    override fun initOnce(savedInstanceState: Bundle?) {}

    override fun initViews(savedInstanceState: Bundle?) {
        binding.tvIdcard.setOnClickListener(this)
        binding.tvIdcardHand.setOnClickListener(this)
        binding.tvCardHealth.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val bundle = Bundle()
        when(v?.id){
            R.id.tv_idcard -> {
                bundle.putString("title","身份证")
            }
            R.id.tv_idcard_hand -> {
                bundle.putString("title","手持证件照")
            }
            R.id.tv_card_health -> {
                bundle.putString("title","健康证")
            }
        }
        findNavController().navigate(R.id.userinfo_certificate_dest,bundle,options)
    }
}