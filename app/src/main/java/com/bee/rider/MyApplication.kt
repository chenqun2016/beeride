package com.bee.rider

import android.app.Application
import cn.jpush.android.api.JPushInterface
import com.chenchen.base.utils.MMKVUtils

/**
 * 创建时间：2021/12/21
 * @Author： 陈陈陈
 * 功能描述：
 */
class MyApplication : Application() {
    companion object {
        var mInstance: MyApplication? = null
    }
    override fun onCreate() {
        super.onCreate()
        mInstance = this
        MMKVUtils.init(this)
        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)
    }
}