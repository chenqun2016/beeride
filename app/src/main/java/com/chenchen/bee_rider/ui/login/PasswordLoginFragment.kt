package com.chenchen.bee_rider.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chenchen.base.base.BaseFragment
import com.chenchen.bee_rider.R
import com.chenchen.bee_rider.databinding.FragmentPasswordLoginBinding
import com.chenchen.bee_rider.utils.options

/**
 * 创建时间：2022/1/22
 * @Author： 陈陈陈
 * 功能描述：
 */
class PasswordLoginFragment :BaseFragment<FragmentPasswordLoginBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentPasswordLoginBinding {
        return FragmentPasswordLoginBinding.inflate(inflater,container,false)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        binding.tvAgree.setOnClickListener {
            findNavController().popBackStack(R.id.code_login_dest,true)
            findNavController().navigate(R.id.home_dest,null, options)
        }
    }
}