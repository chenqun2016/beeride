package com.bee.rider.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chenchen.base.base.BaseFragment
import com.bee.rider.R
import com.bee.rider.databinding.FragmentPasswordLoginBinding
import com.bee.rider.utils.UIUtils
import com.bee.rider.utils.options
import com.bee.rider.utils.setButtonClickableBy

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
    override fun initOnce(savedInstanceState: Bundle?) {}

    override fun initViews(savedInstanceState: Bundle?) {
        binding.ivBack.setOnClickListener(this)
        binding.tvAgree.setOnClickListener(this)
        binding.tvForgetmima.setOnClickListener(this)

        binding.tvAgree.setButtonClickableBy(binding.edUserPhone,binding.edUserPass.editTextView)
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