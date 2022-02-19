package com.chenchen.bee_rider.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chenchen.base.base.BaseFragment
import com.chenchen.bee_rider.R
import com.chenchen.bee_rider.databinding.FragmentResetPasswordBinding
import com.chenchen.bee_rider.view.SendCodeView

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

    override fun initViews(savedInstanceState: Bundle?) {
        binding.ivBack.setOnClickListener(this)
        binding.tvAgree.setOnClickListener(this)

        binding.codeText.initDatas(object : SendCodeView.MyOnClickListener{
            override fun onGetPhoneNum(): String {
                return ""
            }

            override fun onSuccess(t: String?) {
            }

            override fun onFailure(t: String?) {
            }
        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_back,
            R.id.tv_agree -> {
                findNavController().popBackStack()
            }
        }
    }
}