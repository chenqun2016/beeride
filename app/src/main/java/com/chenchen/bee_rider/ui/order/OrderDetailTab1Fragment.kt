package com.chenchen.bee_rider.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.amap.api.location.AMapLocation
import com.amap.api.maps.AMap
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.model.LatLng
import com.amap.api.maps.model.LatLngBounds
import com.amap.api.maps.model.Marker
import com.chenchen.base.base.BaseFragment
import com.chenchen.base.utils.DisplayUtil
import com.chenchen.base.utils.LiveDataBus
import com.chenchen.base.utils.MMKVUtils
import com.chenchen.bee_rider.Constants
import com.chenchen.bee_rider.databinding.FragmentOrderDetailTab1Binding
import com.chenchen.bee_rider.ui.adapter.ProductsAdapter
import com.chenchen.bee_rider.utils.startAlphaAnim
import com.google.android.material.appbar.AppBarLayout
import com.gyf.immersionbar.ImmersionBar
import kotlin.math.abs

/**
 * 创建时间：2022/1/9
 * @Author： 陈陈陈
 * 功能描述：
 */
class OrderDetailTab1Fragment : BaseFragment<FragmentOrderDetailTab1Binding>() , AMap.OnMapLoadedListener,
    AMap.OnInfoWindowClickListener{

    private var aMap: AMap? = null

    override fun onDestroyView() {
        binding.map.onDestroy()
        super.onDestroyView()
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentOrderDetailTab1Binding {
        return FragmentOrderDetailTab1Binding.inflate(inflater, container, false)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        val layoutParams = binding.toolbar.layoutParams
        layoutParams.height = DisplayUtil.dip2px(context,40f)+ImmersionBar.getStatusBarHeight(this)
        binding.toolbar.layoutParams = layoutParams
        binding.toolbar.minimumHeight = layoutParams.height

        binding.includeProducts.products.layoutManager = LinearLayoutManager(context)
        val productsAdapter = ProductsAdapter()
        binding.includeProducts.products.adapter = productsAdapter
        val products = mutableListOf<String>("1","2","3")
        productsAdapter.setNewInstance(products)

        initMap(savedInstanceState)

        binding.appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val c = abs(verticalOffset) == appBarLayout.totalScrollRange
            if(closed != c){
                closed = c
                binding.daohang.startAlphaAnim(!closed)
                LiveDataBus.get().with("reflushToolbar").postValue(closed)
            }
            binding.toolbar.background.alpha = (abs(verticalOffset)/appBarLayout.totalScrollRange.toFloat() * 255).toInt()
        })
    }
    var closed :Boolean = false
    private fun initMap(savedInstanceState: Bundle?) {
        binding.map.onCreate(savedInstanceState)
        if (aMap == null) {
            aMap = binding.map.map
            val mUiSettings = aMap?.uiSettings
            mUiSettings?.isZoomControlsEnabled = false //设置地图默认的缩放按钮是否显示
            mUiSettings?.isMyLocationButtonEnabled = false // 设置地图默认的定位按钮是否显示
        }

//        定位到自己的位置
//        val myLocationStyle: MyLocationStyle
//        myLocationStyle =
//            MyLocationStyle() //初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
//        myLocationStyle.showMyLocation(true)
////        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.
////                fromResource(R.drawable.icon_dengdaichuli));
//        //        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.
////                fromResource(R.drawable.icon_dengdaichuli));
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW) //定位一次，且将视角移动到地图中心点
//        myLocationStyle.interval(2000) //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
//        aMap?.myLocationStyle = myLocationStyle //设置定位蓝点的Style
//        aMap?.isMyLocationEnabled = true // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。


        aMap?.addOnMyLocationChangeListener {
            //从location对象中获取经纬度信息，地址描述信息，建议拿到位置之后调用逆地理编码接口获取（获取地址描述数据章节有介绍）
        }
        aMap?.setOnMapLoadedListener(this) // 设置amap加载成功事件监听器
        aMap?.setOnInfoWindowClickListener(this) // 设置点击infoWindow事件监听器
        aMap?.setOnMarkerClickListener { true }
    }

    /**
     * 监听amap地图加载成功事件回调
     */
    override fun onMapLoaded() {
        val location: AMapLocation? = MMKVUtils.getParcelable(Constants.LOCATION)
        if(null != location){
            val locationBean = LatLng(location.latitude, location.longitude)
            val locationBean1 = LatLng(location.latitude + 0.005, location.longitude - 0.005)

            val windowWidth: Int = DisplayUtil.getWindowWidth(activity)
            val windowHeight: Int = DisplayUtil.getWindowHeight(activity)
            // 设置所有maker显示在当前可视区域地图中
            val bounds = LatLngBounds.Builder()
                .include(locationBean).include(locationBean1).build()
            aMap!!.moveCamera(
                CameraUpdateFactory.newLatLngBoundsRect(
                    bounds,
                    windowWidth / 4,
                    windowWidth / 4,
                    windowHeight / 8,
                    windowHeight * 5 / 8
                )
            )
        }
//        CameraPosition的参数意思：目标可视区域的缩放级别。目标可视区域的倾斜度，以角度为单位。可视区域指向的方向，以角度为单位，从正北向逆时针方向计算，从0 度到360 度。
        // 设置所有maker显示在当前可视区域地图中
//        aMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(
//                locationBean, 15, 0, 0)));
    }

    override fun onInfoWindowClick(marker: Marker?) {
    }


    /**
     * 方法必须重写
     */
    override fun onResume() {
        super.onResume()
        binding.map.onResume()
    }

    /**
     * 方法必须重写
     */
    override fun onPause() {
        super.onPause()
        binding.map.onPause()
    }

    /**
     * 方法必须重写
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.map.onSaveInstanceState(outState)
    }

}