package com.bee.rider.ui

import android.Manifest
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps.MapsInitializer
import com.amap.api.services.core.ServiceSettings
import com.bee.rider.Constants
import com.bee.rider.R
import com.bee.rider.bean.AppUpdateInfoBean
import com.bee.rider.databinding.ActivityMainBinding
import com.bee.rider.params.UpdateInfoParams
import com.bee.rider.service.CheckUpdateService
import com.bee.rider.utils.DeviceUtils
import com.bee.rider.vm.MainViewModel
import com.chenchen.base.base.BaseActivity
import com.chenchen.base.utils.MMKVUtils
import com.chenchen.base.utils.d
import com.permissionx.guolindev.PermissionX

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val viewModel: MainViewModel by viewModels()
    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
    override fun initViews(savedInstanceState: Bundle?) {
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return

        host.navController.addOnDestinationChangedListener { _, destination, _ ->
            val dest: String = try {
                resources.getResourceName(destination.id)
            } catch (e: Resources.NotFoundException) {
                Integer.toString(destination.id)
            }
            d("Navigation", "Navigated to $dest")
        }
        showPrivacy()
    }

    override fun initDatas() {
        viewModel.updateInfo.observe(this, {
          setUpDateInfo(it)
        })
       // viewModel.updateInfo(UpdateInfoParams( DeviceUtils.getAppVersionName(),"Android-USER"))
    }

    private fun setUpDateInfo(it: Result<AppUpdateInfoBean?>) {
        if (it.isSuccess) {
            val bean = it.getOrNull()
            if (bean != null) {
                val isForceUpdate: Int = bean.isForceUpdate //是否强制更新(0:否,1:是)
                // TODO: 2022/3/5 根据是否强制更新展示最新的弹框UI
                startUpdate(bean.url)
            }
        }
    }

    /**
     * 下载更新
     */
    private fun startUpdate(url: String) {
        val intent = Intent(this, CheckUpdateService::class.java)
        intent.putExtra("url", url)
        CheckUpdateService.enqueueWork(this, intent)
    }

    //TODO 隐私政策弹窗
    private fun showPrivacy() {
        //高德地图隐私
        MapsInitializer.updatePrivacyShow(this, true, true)
        MapsInitializer.updatePrivacyAgree(this, true)
        ServiceSettings.updatePrivacyShow(this, true, true)
        ServiceSettings.updatePrivacyAgree(this, true)

        getPermissions()
    }

    private fun getPermissions() {
        val permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        PermissionX.init(this)
            .permissions(permissions)
            .explainReasonBeforeRequest()
            .onExplainRequestReason { scope, deniedList ->
                val message = "需要您同意以下权限才能正常使用"
                scope.showRequestReasonDialog(deniedList, message, "同意", "拒绝")
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    initLocations()
                } else {
//                    Toast.makeText(this, "需要", Toast.LENGTH_LONG).show()
                }
            }
    }


    private var mLocationClient:AMapLocationClient? = null
    private fun initLocations() {

        //初始化定位
        try {
            mLocationClient = AMapLocationClient(applicationContext)

//设置定位回调监听
            mLocationClient?.setLocationListener(mAMapLocationListener)
            val mLocationOption = AMapLocationClientOption()
            //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
            mLocationOption.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy

            //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
            mLocationOption.interval = 2000;
            //获取一次定位结果：
//该方法默认为false。
            mLocationOption.isOnceLocation = false
            //获取最近3s内精度最高的一次定位结果：
//设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
            mLocationOption.isOnceLocationLatest = true
            mLocationClient?.setLocationOption(mLocationOption)
            //启动定位
//            mLocationClient?.startLocation()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //声明定位回调监听器
    //异步获取定位结果
    private val mAMapLocationListener =
        AMapLocationListener { amapLocation ->
            if (amapLocation != null) {
                if (amapLocation.errorCode == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    d("location", "location==" + amapLocation.toStr())
                    MMKVUtils.putParcelable(Constants.LOCATION, amapLocation)
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e(
                        "AmapError", "location Error, ErrCode:"
                                + amapLocation.errorCode + ", errInfo:"
                                + amapLocation.errorInfo
                    )
                }
            }
        }

}