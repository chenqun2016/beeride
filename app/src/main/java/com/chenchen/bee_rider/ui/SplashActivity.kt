package com.chenchen.bee_rider.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.chenchen.base.base.BaseActivity
import com.chenchen.base.constants.HttpConstants
import com.chenchen.base.utils.MMKVUtils
import com.chenchen.bee_rider.MainActivity
import com.chenchen.bee_rider.R
import com.chenchen.bee_rider.databinding.ActivitySplashBinding
import com.chenchen.bee_rider.ui.login.PhoneLoginActivity

/**
 * 创建时间：2021/1/15
 * @Author： 陈陈陈
 * 功能描述：
 */
class SplashActivity : BaseActivity<ActivitySplashBinding>(){

    override fun getViewBinding(): ActivitySplashBinding {
       return ActivitySplashBinding.inflate(layoutInflater)
    }
    override fun initViews(savedInstanceState: Bundle?) {
        if(!TextUtils.isEmpty(MMKVUtils.getString(HttpConstants.TOKEN))){
            startActivity(Intent(this, MainActivity::class.java))
        }else{
            startActivity(Intent(this, PhoneLoginActivity::class.java))
        }
        finish()
    }

    override fun initDatas() {
    }

}