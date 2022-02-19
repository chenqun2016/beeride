package com.chenchen.bee_rider.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chenchen.base.base.BaseFragment
import com.chenchen.bee_rider.R
import com.chenchen.bee_rider.databinding.FragmentPasswordLoginBinding
import com.chenchen.bee_rider.utils.UIUtils
import com.chenchen.bee_rider.utils.options

/**
 * 创建时间：2022/1/22
 * @Author： 陈陈陈
 * 功能描述：
 */
class PasswordLoginFragment :BaseFragment<FragmentPasswordLoginBinding>(), View.OnClickListener {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentPasswordLoginBinding {
        return FragmentPasswordLoginBinding.inflate(inflater,container,false)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        binding.ivBack.setOnClickListener(this)
        binding.tvAgree.setOnClickListener(this)
        binding.tvForgetmima.setOnClickListener(this)

        UIUtils.setXieYiText(this,binding.tvXieyi)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_back -> {
                findNavController().popBackStack()
            }
            R.id.tv_agree -> {
                findNavController().navigate(R.id.next_action_home,null, UIUtils.getNavOptions(R.id.code_login_dest))
            }
            R.id.tv_forgetmima -> {
                findNavController().navigate(R.id.next_action_reset_password,null, options)
            }
        }
    }
}