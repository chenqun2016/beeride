package com.chenchen.bee_rider.ui.setting

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chenchen.base.base.BaseFragment
import com.chenchen.bee_rider.Constants
import com.chenchen.bee_rider.R
import com.chenchen.bee_rider.databinding.FragmentSystemSettingBinding
import com.chenchen.bee_rider.utils.CacheDataManager
import com.chenchen.bee_rider.utils.DeviceUtils
import com.chenchen.bee_rider.utils.options
import com.gyf.immersionbar.ImmersionBar

/**
 - @Description: 系统设置
 - @Author: bxy
 - @Time:  2022/1/16 下午4:23
 */
class SystemSettingFragment : BaseFragment<FragmentSystemSettingBinding>(), View.OnClickListener {

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSystemSettingBinding {
        return FragmentSystemSettingBinding.inflate(inflater, container, false)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        initImmersionBar()
        binding.tvVersionText.text = "当前版本V" + DeviceUtils.getAppVersionName()
        binding.tvCacheText.text = CacheDataManager.getTotalCacheSize(activity)
        binding.tvPasswordSet.setOnClickListener(this)
        binding.tvOrderSet.setOnClickListener(this)
        binding.tvNotificationSet.setOnClickListener(this)
        binding.llClearCache.setOnClickListener(this)
        binding.tvPrivacy.setOnClickListener(this)
        binding.tvServiceAgreement.setOnClickListener(this)
        binding.llVersion.setOnClickListener(this)
        binding.tvAbout.setOnClickListener(this)
    }

    private fun initImmersionBar() {
        val mImmersionBar = ImmersionBar.with(this)
        mImmersionBar.statusBarDarkFont(true, 0.2f)
        mImmersionBar.init()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_password_set -> {
            }

            R.id.tv_order_set -> {
            }

            R.id.tv_notification_set -> {
            }

            R.id.ll_clear_cache -> {
                CacheDataManager.clearAllCache(activity)
                Handler().postDelayed({

                    // 重新获取应用缓存大小
                    binding.tvCacheText.text = CacheDataManager.getTotalCacheSize(activity)
                    // closeLoadingDialog()
                }, 500)
            }

            R.id.tv_privacy -> {
                val bundle = Bundle()
                bundle.putString("url", Constants.agreement_privacy)
                findNavController().navigate(R.id.common_web,bundle, options)
            }

            R.id.tv_service_agreement -> {
                val bundle = Bundle()
                bundle.putString("url", Constants.agreement_regist)
                findNavController().navigate(R.id.common_web,bundle, options)
            }

            R.id.ll_version -> {
            }

            R.id.tv_about ->  findNavController().navigate(R.id.about_fresh_bee,null, options)
        }
    }

}