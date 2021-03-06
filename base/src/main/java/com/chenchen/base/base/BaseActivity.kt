package com.chenchen.base.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.chenchen.base.utils.StatusBar
import com.chenchen.base.utils.d

/**
 * 创建时间：2021/12/20
 * @Author： 陈陈陈
 * 功能描述：
 */
abstract class BaseActivity<VB:ViewBinding> : AppCompatActivity()  {
    protected val TAG: String = this.javaClass.simpleName
    lateinit var binding:VB

    override fun onCreate(savedInstanceState: Bundle?) {
        StatusBar.lightStatusBar(this,true)
        super.onCreate(savedInstanceState)
        d(TAG,"onCreate")
        binding = getViewBinding()
        setContentView(binding.root)

        initViews(savedInstanceState)
        initDatas()
    }

    abstract fun getViewBinding(): VB
    abstract fun initViews(savedInstanceState: Bundle?)
    abstract fun initDatas()


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        d(TAG,"onAttachedToWindow")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        d(TAG,"onDetachedFromWindow")
    }

    override fun onStart() {
        super.onStart()
        d(TAG,"onStart")
    }

    override fun onRestart() {
        super.onRestart()
        d(TAG,"onRestart")
    }

    override fun onResume() {
        super.onResume()
        d(TAG,"onResume")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        d(TAG,"onNewIntent")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        d(TAG,"onSaveInstanceState")
    }

    override fun onPause() {
        super.onPause()
        d(TAG,"onPause")
    }

    override fun onStop() {
        super.onStop()
        d(TAG,"onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        d(TAG,"onDestroy")
    }
}