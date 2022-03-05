package com.bee.rider.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.chenchen.base.base.BaseActivity
import com.chenchen.base.constants.HttpConstants
import com.chenchen.base.utils.MMKVUtils
import com.bee.rider.databinding.ActivitySplashBinding

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
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun initDatas() {
    }

}