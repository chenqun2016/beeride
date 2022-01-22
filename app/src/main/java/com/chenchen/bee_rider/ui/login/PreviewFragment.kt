package com.chenchen.bee_rider.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chenchen.base.base.BaseFragment
import com.chenchen.base.constants.HttpConstants
import com.chenchen.base.utils.MMKVUtils
import com.chenchen.bee_rider.R
import com.chenchen.bee_rider.databinding.FragmentPreviewBinding
import com.chenchen.bee_rider.utils.options

/**
 * 创建时间：2022/1/22
 * @Author： 陈陈陈
 * 功能描述：
 */
class PreviewFragment :BaseFragment<FragmentPreviewBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentPreviewBinding {
        return FragmentPreviewBinding.inflate(inflater,container,false)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        binding.tvConfirm.setOnClickListener {
            if(!TextUtils.isEmpty(MMKVUtils.getString(HttpConstants.TOKEN))){
                findNavController().popBackStack(R.id.preview_dest,true)
                findNavController().navigate(R.id.home_dest,null, options)
            }else{
                findNavController().popBackStack(R.id.preview_dest,true)
                findNavController().navigate(R.id.code_login_dest,null, options)
            }
        }
    }
}