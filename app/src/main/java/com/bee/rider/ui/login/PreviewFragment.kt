package com.bee.rider.ui.login

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chenchen.base.base.BaseFragment
import com.chenchen.base.constants.HttpConstants
import com.chenchen.base.utils.MMKVUtils
import com.bee.rider.R
import com.bee.rider.databinding.FragmentPreviewBinding
import com.bee.rider.utils.UIUtils

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
    override fun initOnce(savedInstanceState: Bundle?) {}

    override fun initViews(savedInstanceState: Bundle?) {
        binding.tvConfirm.setOnClickListener {
            if(!TextUtils.isEmpty(MMKVUtils.getString(HttpConstants.TOKEN))){
                findNavController().navigate(R.id.home_dest,null, UIUtils.getNavOptions(R.id.preview_dest))
            }else{
                findNavController().navigate(R.id.code_login_dest,null, UIUtils.getNavOptions(R.id.preview_dest))
            }
        }
    }
}