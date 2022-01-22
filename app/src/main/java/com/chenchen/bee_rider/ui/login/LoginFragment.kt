package com.chenchen.bee_rider.ui.login

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.blankj.utilcode.util.KeyboardUtils
import com.chenchen.base.base.BaseFragment
import com.chenchen.bee_rider.R
import com.chenchen.bee_rider.databinding.FragmentCodeLoginBinding
import com.chenchen.bee_rider.utils.options
import com.chenchen.bee_rider.view.SendCodeView

/**
 * 创建时间：2022/1/22
 * @Author： 陈陈陈
 * 功能描述：
 */
class LoginFragment : BaseFragment<FragmentCodeLoginBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCodeLoginBinding {
        return FragmentCodeLoginBinding.inflate(inflater,container,false)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.tvMimalogin.setOnClickListener {
            findNavController().navigate(R.id.password_login_dest,null, options)
        }
        binding.codeText.initDatas(object : SendCodeView.MyOnClickListener{
            override fun onGetPhoneNum(): String {
                return ""
            }

            override fun onSuccess(t: String?) {
            }

            override fun onFailure(t: String?) {
            }
        })
        binding.edUserPhone.addTextChangedListener{
            setButtonStatus()
        }
        binding.edUserCode.addTextChangedListener {
            setButtonStatus()
        }
        binding.tvAgree.setOnClickListener {
            if(null != activity){
                KeyboardUtils.hideSoftInput(requireActivity())
            }
            findNavController().popBackStack(R.id.code_login_dest,true)
            findNavController().navigate(R.id.home_dest,null, options)
        }
    }

    private fun setButtonStatus() {
        if (!TextUtils.isEmpty(binding.edUserCode.text)&&!TextUtils.isEmpty(binding.edUserPhone.text)) {
            binding.tvAgree.isEnabled = true
            binding.tvAgree.setBackgroundResource(R.drawable.btn_gradient_yellow_round)
        } else {
            binding.tvAgree.isEnabled = false
            binding.tvAgree.setBackgroundResource(R.drawable.btn_gradient_grey_round)
        }
    }
}