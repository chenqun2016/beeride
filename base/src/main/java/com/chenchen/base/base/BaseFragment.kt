package com.chenchen.base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.chenchen.base.utils.d


/**
 * 创建时间：2022/1/3
 * @Author： 陈陈陈
 * 功能描述：
 */
abstract class BaseFragment <VB:ViewBinding> :Fragment(){
    protected val TAG: String = this.javaClass.simpleName

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    abstract fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        d(TAG,"onCreateView")
        _binding = getBinding(inflater,container,savedInstanceState)
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        d(TAG,"onDestroyView")
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(savedInstanceState)
        d(TAG,"onViewCreated")
    }

    abstract fun initViews(savedInstanceState: Bundle?)

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
    override fun onDestroy() {
        super.onDestroy()
        d(TAG,"onDestroy")
    }
}