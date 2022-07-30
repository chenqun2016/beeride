package com.bee.rider.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chenchen.base.base.BaseFragment
import com.bee.rider.R
import com.bee.rider.databinding.FragmentResetPasswordBinding
import com.bee.rider.params.ResetPasswordParams
import com.bee.rider.utils.UIUtils
import com.bee.rider.utils.setButtonClickableBy
import com.bee.rider.view.SendCodeView
import com.bee.rider.vm.LoginViewModel
import com.chenchen.base.utils.MMKVUtils

/**
 * 创建时间：2022/2/6
 * @Author： 陈陈陈
 * 功能描述：
 */
class ResetPasswordFragment : BaseFragment<FragmentResetPasswordBinding>(), View.OnClickListener {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentResetPasswordBinding {
        return FragmentResetPasswordBinding.inflate(inflater)
    }
    private val viewModel: LoginViewModel by viewModels()

    override fun initOnce(savedInstanceState: Bundle?) {
        viewModel.resetPassword.observe(this,{
            if(it.isSuccess){
                MMKVUtils.clearAll()
                findNavController().popBackStack()
            }
        })
    }

    override fun initViews(savedInstanceState: Bundle?) {
        binding.ivBack.setOnClickListener(this)
        binding.tvAgree.setOnClickListener(this)

        binding.codeText.initDatas(binding.edUserPhone,binding.edUserCode,lifecycle)
        binding.tvAgree.setButtonClickableBy(binding.edUserPhone,binding.edUserCode,binding.edUserPass.editTextView)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_back -> {
                findNavController().popBackStack()
            }
            R.id.tv_agree -> {
                viewModel.doResetPassword(ResetPasswordParams(binding.edUserPass.editTextView.text.toString(),binding.edUserCode.text.toString(),binding.edUserPhone.text.toString(),binding.edUserPhone.text.toString()))
            }
        }
    }
}