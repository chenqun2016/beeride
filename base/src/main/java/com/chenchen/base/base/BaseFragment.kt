package com.chenchen.base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.chenchen.base.utils.d
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.components.ImmersionFragment


/**
 * 创建时间：2022/1/3
 * @Author： 陈陈陈
 * 功能描述：
 */
abstract class BaseFragment <VB:ViewBinding> : ImmersionFragment(){
    protected val TAG: String = this.javaClass.simpleName
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        d(TAG,"onCreate")
        initOnce(savedInstanceState)
    }
    /**
     * 因Navigation框架限制，每次跳转新页面都会导致当前页面重走 onCreateView()--onDestroyView()方法
     *
     * 此方法生命周期里只走一次
     * 用于初始化 viewmodel 等只需要初始化一次的代码
     */
    abstract fun initOnce(savedInstanceState: Bundle?)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        d(TAG,"onCreateView")
        _binding = getBinding(inflater,container,savedInstanceState)
        return _binding?.root
    }
    abstract fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): VB

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(savedInstanceState)
        d(TAG,"onViewCreated")
    }
    /**
     * 只初始化跟view相关的
     * 其他放在initOnce()里初始化
     * 注意：viewmodel.obsever()不能在此方法中调用
     */
    abstract fun initViews(savedInstanceState: Bundle?)

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
        _binding = null
        d(TAG,"onDestroyView")
    }
    override fun onDestroy() {
        super.onDestroy()
        d(TAG,"onDestroy")
    }

    override fun initImmersionBar() {
        val mImmersionBar = ImmersionBar.with(this)
        mImmersionBar.statusBarDarkFont(true, 0.2f)
        mImmersionBar.init()
    }
    override fun immersionBarEnabled(): Boolean {
        return true
    }

}