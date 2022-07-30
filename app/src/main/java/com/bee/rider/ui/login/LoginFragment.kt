package com.bee.rider.ui.login

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bee.rider.R
import com.bee.rider.databinding.FragmentCodeLoginBinding
import com.bee.rider.params.SmsCodeLoginParams
import com.bee.rider.utils.UIUtils
import com.bee.rider.utils.options
import com.bee.rider.utils.setButtonClickableBy
import com.bee.rider.vm.LoginViewModel
import com.blankj.utilcode.util.KeyboardUtils
import com.chenchen.base.base.BaseFragment
import com.chenchen.base.constants.HttpConstants
import com.chenchen.base.utils.MMKVUtils

/**
 * 创建时间：2022/1/22
 * @Author： 陈陈陈
 * 功能描述：验证码登录
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
    override fun initOnce(savedInstanceState: Bundle?) {
        viewModel.smsLogin.observe(this,{
            //登录成功
            if(it.isSuccess){
                val token = it.getOrNull()
                if(null != token){
                    MMKVUtils.putString(HttpConstants.TOKEN,token)
                    findNavController().navigate(R.id.next_action_home,null, UIUtils.getNavOptions(R.id.code_login_dest))
                }
            }
        })
    }

    override fun initViews(savedInstanceState: Bundle?) {
        binding.ivBack.setOnClickListener(this)
        binding.tvMimalogin.setOnClickListener(this)
        binding.tvAgree.setOnClickListener(this)

        binding.codeText.initDatas(binding.edUserPhone,binding.edUserCode,lifecycle)
        binding.tvAgree.setButtonClickableBy(binding.edUserPhone,binding.edUserCode,other = binding.codeText)
        UIUtils.setXieYiText(this,binding.tvXieyi)

        //TODO 测试账号
        binding.edUserPhone.setText("18800000000")
        binding.edUserCode.setText("123456")
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_agree -> {
                if(null != activity){
                    KeyboardUtils.hideSoftInput(requireActivity())
                }
                if(!binding.checkbox.isChecked){
                    showCommonDialog()
                    return
                }
                viewModel.doSmsLogin(SmsCodeLoginParams(binding.edUserPhone.text.toString(),binding.edUserCode.text.toString()))
            }
            R.id.iv_back -> {
                activity?.finish()
            }
            R.id.tv_mimalogin -> {
                findNavController().navigate(R.id.next_action_password_login,null,options)
            }
        }
    }

    private fun showCommonDialog() {
        val dialog = context?.let { Dialog(it, R.style.loadingDialogTheme) }
        val inflate = View.inflate(context, R.layout.dialog_hint, null)
        val tv_des = inflate.findViewById<TextView>(R.id.tv_des)
        UIUtils.setXieYiText(this,tv_des,"提示：请先阅读并同意趣鲜蜂")
        val tv_quxiao = inflate.findViewById<TextView>(R.id.btn_cancel)
        val tv_queding = inflate.findViewById<TextView>(R.id.btn_sure)
        tv_quxiao.setOnClickListener {
            dialog?.dismiss()
        }
        tv_queding.setOnClickListener {
            if(isBindingViewCreated()){
                binding.checkbox.isChecked = true
            }
            dialog?.dismiss()
        }
        dialog?.setContentView(inflate)
        dialog?.show()
    }
}