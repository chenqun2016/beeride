package com.bee.rider.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alibaba.idst.nui.CommonUtils
import com.chenchen.base.base.BaseFragment
import com.bee.rider.R
import com.bee.rider.bean.UserBean
import com.bee.rider.databinding.FragmentPasswordSettingBinding
import com.bee.rider.utils.UIUtils
import com.bee.rider.utils.options
import com.bee.rider.utils.setButtonClickableBy
import com.bee.rider.view.SendCodeView
import com.bee.rider.vm.LoginViewModel
import com.chenchen.base.utils.MMKVUtils

/**
 * 创建时间：2022/2/20
 * @Author： 陈陈陈
 * 功能描述：
 */
class PasswordSettingFragment : BaseFragment<FragmentPasswordSettingBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentPasswordSettingBinding {
        return FragmentPasswordSettingBinding.inflate(inflater)
    }
    private val viewModel: LoginViewModel by viewModels()

    override fun initOnce(savedInstanceState: Bundle?) {
        viewModel.checkSmsCode.observe(this,{
            if(it.isSuccess){
                val args = Bundle()
                args.putString("code", binding.edUserCode.text.toString())
                args.putString("phone", userBean?.linkPhone)
                args.putString("accountNo", userBean?.accountNo)

                findNavController().navigate(R.id.password_setting2_dest, args, options)
            }
        })
    }
    var userBean :UserBean? = null
    override fun initViews(savedInstanceState: Bundle?) {
        binding.codeText.initDatas(binding.tvPhone,binding.edUserCode,lifecycle)
        binding.tvConfirm.setButtonClickableBy(binding.edUserCode)

        userBean  = MMKVUtils.getObject(UserBean::class.java)
        if(null != userBean){
            binding.tvPhone.tag = userBean?.linkPhone
            binding.tvPhone.text = UIUtils.getPhoneText(userBean?.linkPhone)
        }
        binding.tvConfirm.setOnClickListener {
//            if(!binding.codeText.hasSendCode){
//                Toast.makeText(context, "请先获取验证码", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
          viewModel.doCheckSmsCode(userBean?.linkPhone ?: "",binding.edUserCode.text.toString())
        }
        binding.codeText.tvGetCode?.performClick()
    }
}