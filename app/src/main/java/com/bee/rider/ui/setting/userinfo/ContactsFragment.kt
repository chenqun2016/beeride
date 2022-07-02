package com.bee.rider.ui.setting.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bee.rider.bean.UserBean
import com.chenchen.base.base.BaseFragment
import com.bee.rider.databinding.FragmentContactsBinding
import com.chenchen.base.utils.MMKVUtils

/**
 * 创建时间：2022/2/26
 * @Author： 陈陈陈
 * 功能描述：
 */
class ContactsFragment : BaseFragment<FragmentContactsBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentContactsBinding {
        return FragmentContactsBinding.inflate(inflater)
    }
    override fun initOnce(savedInstanceState: Bundle?) {}

    override fun initViews(savedInstanceState: Bundle?) {
        val userInfo = MMKVUtils.getObject(UserBean::class.java)
        if(null != userInfo) {
            binding.tvIdcardResult.text = userInfo.emergencyContactName
            binding.tvIdcardHandResult.text = if(userInfo.emergencyContactPhone.length>10) userInfo.emergencyContactPhone.substring(0, 3) + " ****** " + userInfo.emergencyContactPhone.substring(9, 11) else userInfo.emergencyContactPhone
            binding.tvCardHealthResult.text = userInfo.emergencyContactRelationship
        }

    }
}