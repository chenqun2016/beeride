package com.chenchen.bee_rider.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chenchen.base.base.BaseFragment
import com.chenchen.bee_rider.R
import com.chenchen.bee_rider.databinding.FragmentPasswordSettingBinding
import com.chenchen.bee_rider.utils.options
import com.chenchen.bee_rider.utils.setButtonClickableBy
import com.chenchen.bee_rider.view.SendCodeView

/**
 * 创建时间：2022/2/20
 * @Author： 陈陈陈
 * 功能描述：
 */
class PasswordSettingFragment : BaseFragment<FragmentPasswordSettingBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentPasswordSettingBinding {
        return FragmentPasswordSettingBinding.inflate(inflater)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        binding.codeText.initDatas(object :SendCodeView.MyOnClickListener{
            override fun onGetPhoneNum(): String {
                return ""
            }
            override fun onSuccess(t: String?) {
            }
            override fun onFailure(t: String?) {
            }
        })
        binding.tvConfirm.setButtonClickableBy(binding.edUserCode)

        binding.tvConfirm.setOnClickListener {
            findNavController().navigate(R.id.password_setting2_dest, Bundle(), options)
        }
    }
}