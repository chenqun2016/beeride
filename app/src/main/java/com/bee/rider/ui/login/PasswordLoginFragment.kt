package com.bee.rider.ui.login

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bee.rider.Constants
import com.chenchen.base.base.BaseFragment
import com.bee.rider.R
import com.bee.rider.databinding.FragmentPasswordLoginBinding
import com.bee.rider.params.LoginParams
import com.bee.rider.utils.UIUtils
import com.bee.rider.utils.options
import com.bee.rider.utils.setButtonClickableBy
import com.bee.rider.vm.LoginViewModel
import com.blankj.utilcode.util.KeyboardUtils
import com.chenchen.base.constants.HttpConstants
import com.chenchen.base.utils.MMKVUtils
import kotlinx.coroutines.launch

/**
 * 创建时间：2022/1/22
 * @Author： 陈陈陈
 * 功能描述：密码登录
 */
class PasswordLoginFragment :BaseFragment<FragmentPasswordLoginBinding>(), View.OnClickListener {
    private val viewModel: LoginViewModel by viewModels()

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentPasswordLoginBinding {
        return FragmentPasswordLoginBinding.inflate(inflater,container,false)
    }
    override fun initOnce(savedInstanceState: Bundle?) {
        viewModel.passwordLogin.observe(this,{
            //登录成功
            if(it.isSuccess){
                val token = it.getOrNull()
                if(null != token){
                    findNavController().navigate(R.id.next_action_home,null, UIUtils.getNavOptions(R.id.code_login_dest))
                }
            }
        })
    }

    override fun initViews(savedInstanceState: Bundle?) {
        binding.ivBack.setOnClickListener(this)
        binding.tvAgree.setOnClickListener(this)
        binding.tvForgetmima.setOnClickListener(this)

        binding.tvAgree.setButtonClickableBy(binding.edUserPhone,binding.edUserPass.editTextView)
        UIUtils.setXieYiText(this,binding.tvXieyi)

        //TODO 测试账号
        binding.edUserPhone.setText("test01")
        binding.edUserPass.editTextView.setText("123456")
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_back -> {
                findNavController().popBackStack()
            }
            R.id.tv_agree -> {
                if(null != activity){
                    KeyboardUtils.hideSoftInput(requireActivity())
                }
                if(!binding.checkbox.isChecked){
                    showCommonDialog()
                    return
                }
                viewModel.doPasswordLogin(LoginParams(binding.edUserPhone.text.toString(),binding.edUserPass.editTextView.text.toString()))
            }
            R.id.tv_forgetmima -> {
                findNavController().navigate(R.id.next_action_reset_password,null, options)
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