package com.chenchen.bee_rider.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.core.widget.addTextChangedListener
import com.chenchen.base.base.BaseActivity
import com.chenchen.bee_rider.ui.MainActivity
import com.chenchen.bee_rider.R
import com.chenchen.bee_rider.databinding.ActivityCodeLoginBinding
import com.chenchen.bee_rider.view.SendCodeView

/**
 * 创建时间：2022/1/15
 * @Author： 陈陈陈
 * 功能描述：
 */
class PhoneLoginActivity : BaseActivity<ActivityCodeLoginBinding>() {

    override fun getViewBinding(): ActivityCodeLoginBinding {
        return ActivityCodeLoginBinding.inflate(layoutInflater)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        binding.ivBack.setOnClickListener {
            finish()
        }
        binding.codeText.initDatas(object :SendCodeView.MyOnClickListener{
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
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun initDatas() {
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