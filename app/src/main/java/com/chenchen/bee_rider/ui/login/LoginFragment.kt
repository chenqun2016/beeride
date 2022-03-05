package com.chenchen.bee_rider.ui.login

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.blankj.utilcode.util.KeyboardUtils
import com.chenchen.base.base.BaseFragment
import com.chenchen.bee_rider.Constants
import com.chenchen.bee_rider.R
import com.chenchen.bee_rider.databinding.FragmentCodeLoginBinding
import com.chenchen.bee_rider.params.LoginParams
import com.chenchen.bee_rider.utils.UIUtils
import com.chenchen.bee_rider.utils.options
import com.chenchen.bee_rider.utils.setButtonClickableBy
import com.chenchen.bee_rider.view.SendCodeView
import com.chenchen.bee_rider.vm.LoginViewModel

/**
 * 创建时间：2022/1/22
 * @Author： 陈陈陈
 * 功能描述：
 */
class LoginFragment : BaseFragment<FragmentCodeLoginBinding>(), View.OnClickListener {
    private val viewModel: LoginViewModel by viewModels()


    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCodeLoginBinding {
        return FragmentCodeLoginBinding.inflate(inflater,container,false)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        binding.ivBack.setOnClickListener(this)
        binding.tvMimalogin.setOnClickListener(this)
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
        binding.tvAgree.setButtonClickableBy(binding.edUserPhone,binding.edUserCode)
        UIUtils.setXieYiText(this,binding.tvXieyi)

        viewModel.login.observe(this,{
            val bean = it.getOrNull()
            //登录成功
            if(null != bean){
                findNavController().navigate(R.id.next_action_home,null, UIUtils.getNavOptions(R.id.code_login_dest))
            }
        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_agree -> {
                if(null != activity){
                    KeyboardUtils.hideSoftInput(requireActivity())
                }
                val params = LoginParams("","")
                viewModel.doLogin(params)
            }
            R.id.iv_back -> {
                activity?.finish()
            }
            R.id.tv_mimalogin -> {
                findNavController().navigate(R.id.next_action_password_login,null,options)
            }
        }
    }
}