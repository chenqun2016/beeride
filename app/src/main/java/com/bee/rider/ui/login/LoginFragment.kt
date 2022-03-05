package com.bee.rider.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bee.rider.Constants
import com.blankj.utilcode.util.KeyboardUtils
import com.chenchen.base.base.BaseFragment
import com.bee.rider.R
import com.bee.rider.databinding.FragmentCodeLoginBinding
import com.bee.rider.params.LoginParams
import com.bee.rider.utils.UIUtils
import com.bee.rider.utils.options
import com.bee.rider.utils.setButtonClickableBy
import com.bee.rider.view.SendCodeView
import com.bee.rider.vm.LoginViewModel
import com.chenchen.base.constants.HttpConstants
import com.chenchen.base.utils.MMKVUtils

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
            //登录成功
            if(it.isSuccess){
                val bean = it.getOrNull()
                if(null != bean){
                    MMKVUtils.putString(HttpConstants.TOKEN,bean.token)
                    MMKVUtils.putInt(Constants.HORSEMANID,bean.id)
                    findNavController().navigate(R.id.next_action_home,null, UIUtils.getNavOptions(R.id.code_login_dest))
                }
            }
        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_agree -> {
                if(null != activity){
                    KeyboardUtils.hideSoftInput(requireActivity())
                }
                viewModel.doLogin(LoginParams(binding.edUserPhone.text.toString(),binding.edUserCode.text.toString()))
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