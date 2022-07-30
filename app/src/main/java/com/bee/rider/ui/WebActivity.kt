package com.bee.rider.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bee.rider.R
import com.bee.rider.databinding.ActivityWebBinding
import com.bee.rider.ui.setting.CommonWebFragment
import com.chenchen.base.base.BaseActivity

/**
 * 创建时间：2022/7/30
 * 编写人： 陈陈陈
 * 功能描述：
 */
class WebActivity : BaseActivity<ActivityWebBinding>() {
    override fun getViewBinding(): ActivityWebBinding {
        return ActivityWebBinding.inflate(layoutInflater)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        val commonWebFragment = CommonWebFragment()
        val bundle = Bundle()
        bundle.putString("url", intent.getStringExtra("url"))
        commonWebFragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.fl_content,commonWebFragment).commitAllowingStateLoss()
    }

    override fun initDatas() {
    }
    companion object{
        fun startWebActivity(url:String,context:Context?){
            val intent = Intent(context, WebActivity::class.java)
            intent.putExtra("url",url)
            context?.startActivity(intent)
        }
    }
}