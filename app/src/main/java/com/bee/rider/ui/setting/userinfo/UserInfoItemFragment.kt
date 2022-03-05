package com.bee.rider.ui.setting.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.chenchen.base.base.BaseFragment
import com.bee.rider.Constants.ITEMRESULT
import com.bee.rider.databinding.FragmentUserinfoBinding
import com.bee.rider.databinding.FragmentUserinfoItemBinding

/**
 * 创建时间：2022/2/26
 * @Author： 陈陈陈
 * 功能描述：
 */
class UserInfoItemFragment : BaseFragment<FragmentUserinfoItemBinding>() {

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentUserinfoItemBinding {
        return FragmentUserinfoItemBinding.inflate(inflater)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        val type = arguments?.getInt("type", 0)
        val str1 = arguments?.getString("str1", "")
        val str2 = arguments?.getString("str2", "")
        val str3 = arguments?.getString("str3", "")

        binding.tv1.text = str1
        binding.tv2.text = str2
        binding.tv3.setText(str3)

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.tvSure.setOnClickListener {
            parentFragmentManager.setFragmentResult(
                ITEMRESULT,
                bundleOf("type" to type, "text" to binding.tv3.text.toString())
            )
            findNavController().popBackStack()
        }
    }
}