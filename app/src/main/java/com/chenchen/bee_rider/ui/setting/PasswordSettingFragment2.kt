package com.chenchen.bee_rider.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chenchen.base.base.BaseFragment
import com.chenchen.bee_rider.R
import com.chenchen.bee_rider.databinding.FragmentPasswordSetting2Binding
import com.chenchen.bee_rider.utils.UIUtils
import com.chenchen.bee_rider.utils.options
import com.chenchen.bee_rider.utils.setButtonClickableBy
import com.chenchen.bee_rider.view.SendCodeView

/**
 * 创建时间：2022/2/20
 * @Author： 陈陈陈
 * 功能描述：
 */
class PasswordSettingFragment2 : BaseFragment<FragmentPasswordSetting2Binding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentPasswordSetting2Binding {
        return FragmentPasswordSetting2Binding.inflate(inflater)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        binding.edUserPass.editTextView.hint = "请输入6-24位密码"
        binding.edUserPass2.editTextView.hint = "请重新输入6-24位密码"

        binding.tvConfirm.setButtonClickableBy(
            binding.edUserPass.editTextView,
            binding.edUserPass2.editTextView
        )
        binding.tvConfirm.setOnClickListener {
            findNavController().popBackStack(R.id.system_set_dest,false)
        }
    }
}