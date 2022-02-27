package com.chenchen.base.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import com.chenchen.base.R

/**
 * 创建时间：2022/2/26
 * @Author： 陈陈陈
 * 功能描述：
 */
abstract class BaseBottomDialog(context: Context) : Dialog(context, R.style.AddressDialog) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(provideContentViewId())

        initViews(this)
        initPosition()
    }

    abstract fun initViews(dialog: BaseBottomDialog)

    abstract fun provideContentViewId(): Int

    private fun initPosition() {
        // 获取到窗体
        val window = window
        // 获取到窗体的属性
        var params: WindowManager.LayoutParams? = null
        if (window != null) {
            params = window.attributes
            params.height = WindowManager.LayoutParams.WRAP_CONTENT
            params.width = WindowManager.LayoutParams.MATCH_PARENT
            // 让对话框展示到屏幕的下边
            params.gravity = Gravity.BOTTOM
            window.attributes = params
        }
    }
}