package com.bee.rider.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chenchen.base.base.BaseFragment
import com.bee.rider.R
import com.bee.rider.databinding.FragmentPasswordSetting2Binding
import com.bee.rider.params.ResetPasswordParams
import com.bee.rider.utils.UIUtils
import com.bee.rider.utils.setButtonClickableBy
import com.bee.rider.vm.LoginViewModel
import com.chenchen.base.utils.MMKVUtils

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

    private val viewModel: LoginViewModel by viewModels()


    override fun initOnce(savedInstanceState: Bundle?) {
        viewModel.resetPassword.observe(this,{
            if(it.isSuccess){
                MMKVUtils.clearAll()
                findNavController().navigate(R.id.code_login_dest,null, UIUtils.getNavOptions(R.id.home_dest))
            }
        })
    }

    override fun initViews(savedInstanceState: Bundle?) {
        binding.edUserPass.editTextView.hint = "请输入6-24位密码"
        binding.edUserPass2.editTextView.hint = "请重新输入6-24位密码"

        binding.tvConfirm.setButtonClickableBy(
            binding.edUserPass.editTextView,
            binding.edUserPass2.editTextView
        )
        binding.tvConfirm.setOnClickListener {
            if(binding.edUserPass.editTextView.text.toString() != binding.edUserPass2.editTextView.text.toString()){
                Toast.makeText(context, "密码不一致", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val code = arguments?.getString("code") ?: ""
            val phone = arguments?.getString("phone") ?: ""
            val accountNo = arguments?.getString("accountNo") ?: ""
            viewModel.doResetPassword(ResetPasswordParams(binding.edUserPass2.editTextView.text.toString(),code,accountNo,phone))
        }
    }
}