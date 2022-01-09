package com.chenchen.base.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.chenchen.base.utils.d


/**
 * 创建时间：2022/1/3
 * @Author： 陈陈陈
 * 功能描述：
 */
open class BaseFragment :Fragment(){
    protected val TAG: String = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        d(TAG,"onCreate")
    }

    override fun onStart() {
        super.onStart()
        d(TAG,"onStart")
    }

    override fun onResume() {
        super.onResume()
        d(TAG,"onResume")
    }

    override fun onPause() {
        super.onPause()
        d(TAG,"onPause")
    }

    override fun onStop() {
        super.onStop()
        d(TAG,"onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        d(TAG,"onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        d(TAG,"onDestroy")
    }
}