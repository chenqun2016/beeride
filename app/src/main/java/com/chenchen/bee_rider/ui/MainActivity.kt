package com.chenchen.bee_rider.ui

import android.Manifest
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps.MapsInitializer
import com.amap.api.services.core.ServiceSettings
import com.chenchen.base.base.BaseActivity
import com.chenchen.base.utils.MMKVUtils
import com.chenchen.base.utils.d
import com.chenchen.bee_rider.Constants
import com.chenchen.bee_rider.R
import com.chenchen.bee_rider.databinding.ActivityMainBinding
import com.permissionx.guolindev.PermissionX

class MainActivity : BaseActivity<ActivityMainBinding>() {

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
    }

    //TODO 隐私政策弹窗
    private fun showPrivacy() {
        //高德地图隐私
        MapsInitializer.updatePrivacyShow(this,true,true)
        MapsInitializer.updatePrivacyAgree(this,true)
        ServiceSettings.updatePrivacyShow(this,true,true)
        ServiceSettings.updatePrivacyAgree(this,true)

        getPermissions()
    }

    private fun getPermissions() {
        val permissions = listOf(Manifest.permission.ACCESS_FINE_LOCATION,
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
                    d("location","location==" + amapLocation.toStr())
                    MMKVUtils.putParcelable(Constants.LOCATION,amapLocation)
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