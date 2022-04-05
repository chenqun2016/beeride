package com.bee.rider.ui.order

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.amap.api.location.AMapLocation
import com.amap.api.maps.AMap
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.model.LatLng
import com.amap.api.maps.model.LatLngBounds
import com.amap.api.maps.model.Marker
import com.bee.rider.Constants
import com.bee.rider.R
import com.bee.rider.bean.OrderDetailBean
import com.bee.rider.databinding.FragmentOrderDetailTab1Binding
import com.bee.rider.http.NetworkApi
import com.bee.rider.params.InitiativeCreateParams
import com.bee.rider.ui.adapter.ProductsAdapter
import com.bee.rider.utils.UIUtils
import com.bee.rider.utils.startAlphaAnim
import com.bee.rider.vm.OrderDetailViewModel
import com.chenchen.base.base.BaseFragment
import com.chenchen.base.constants.HttpConstants
import com.chenchen.base.utils.DisplayUtil
import com.chenchen.base.utils.LiveDataBus
import com.chenchen.base.utils.MMKVUtils
import com.google.android.material.appbar.AppBarLayout
import com.gyf.immersionbar.ImmersionBar
import kotlinx.coroutines.launch
import kotlin.math.abs

/**
 * 创建时间：2022/1/9
 * @Author： 陈陈陈
 * 功能描述：
 */
class OrderDetailTab1Fragment : BaseFragment<FragmentOrderDetailTab1Binding>() , AMap.OnMapLoadedListener,
    AMap.OnInfoWindowClickListener, View.OnClickListener {

    companion object{
        fun newInstance(id: String?): OrderDetailTab1Fragment{
            val args = Bundle()
            args.putString(Constants.TAKEOUTID, id)
            val fragment = OrderDetailTab1Fragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val viewModel: OrderDetailViewModel by viewModels()
    var mData :OrderDetailBean? = null
    var productsAdapter :ProductsAdapter? = null

    private var aMap: AMap? = null

    override fun initOnce(savedInstanceState: Bundle?) {
        viewModel.orderDetail.observe(this,{
            if (it.isSuccess){
                mData = it.getOrNull()
                if(null != mData){
                    setDatas(mData!!)
                }
            }
        })
    }

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


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_accept -> {
                val param = InitiativeCreateParams(MMKVUtils.getString(HttpConstants.HORSEMANID,""),mData?.disTakeout?.id,mData?.disTakeout?.id)
                viewModel.viewModelScope.launch {
                    val it = NetworkApi.initiativeCreate(param)
                    if (it.isSuccess) {
                        val bean = it.getOrNull()
                        Toast.makeText(context, "接单成功", Toast.LENGTH_SHORT).show()
                        binding.includeDetail.includeOrderItem.tvAccept.visibility = View.GONE
                    }
                }
            }
            R.id.tv_contact -> {
                startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mData?.shopStoreDetailVO?.contactMobile)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            }
            R.id.tv_copy -> {
                context?.let { it1 ->
                    UIUtils.copyContentToClipboard(binding.includeMessages.tvOrdernumDes.text.toString(),
                        it1
                    )
                }
            }
        }
    }

    override fun initViews(savedInstanceState: Bundle?) {
        val layoutParams = binding.toolbar.layoutParams
        layoutParams.height = DisplayUtil.dip2px(context,40f)+ImmersionBar.getStatusBarHeight(this)
        binding.toolbar.layoutParams = layoutParams
        binding.toolbar.minimumHeight = layoutParams.height
        binding.includeProducts.products.layoutManager = LinearLayoutManager(context)
        productsAdapter = ProductsAdapter()
        binding.includeProducts.products.adapter = productsAdapter

        binding.includeDetail.includeOrderItem.tvContact.visibility = View.VISIBLE
        binding.includeMessages.tvCopy.setOnClickListener(this)
        binding.includeDetail.includeOrderItem.tvAccept.setOnClickListener(this)
        binding.includeDetail.includeOrderItem.tvContact.setOnClickListener(this)

        initMap(savedInstanceState)

        binding.appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val c = abs(verticalOffset) == appBarLayout.totalScrollRange
            if(closed != c){
                closed = c
                binding.daohang.startAlphaAnim(!closed)
                LiveDataBus.get().with("reflushToolbar").postValue(closed)
                binding.map.startAlphaAnim(!closed,true)
            }
            binding.map.translationY = verticalOffset*0.5f
        })


        val id = arguments?.getString(Constants.TAKEOUTID)
        viewModel.doOrderDetail(id+"")
    }

    private fun setDatas(item: OrderDetailBean) {
        binding.includeDetail.includeOrderItem.tvAccept.visibility = if(item.queryStatus == 10) View.VISIBLE else View.GONE
        binding.includeDetail.includeOrderItem.tvRight.text = "#"
        binding.includeDetail.includeOrderItem.tvTime.text = "${item.receiveTime}前送达"
        binding.includeDetail.includeOrderItem.tvStoreName.text = item.storeName
        binding.includeDetail.includeOrderItem.tvStoreAddress.text = item.shopStoreDetailVO.addressDetail
        binding.includeDetail.includeOrderItem.tvCustomerAddress.text = item.receiverDetailAddress
        binding.includeDetail.includeOrderItem.tvCustomerNamePhone.text = "${item.receiverName}/${item.receiverPhone}"
//TODO
        binding.includeDetail.includeOrderItem.tvTime2.text = "(系统派送)"
        binding.includeDetail.includeOrderItem.tvStoreDistance.text = "距离1.5km"
        binding.includeDetail.includeOrderItem.tvCustomerDistance.text = "距离1.5km"

        binding.includeProducts.tvStore.text = item.storeName
        productsAdapter?.setNewInstance(item.orderItemList)

        binding.includeProducts.itemDes.tvBaozhuangfeiValue.text =  "¥${item.packingFeeAmount}"
        binding.includeProducts.itemDes.tvPeisongfeiValue.text = "¥${item.freightAmount}"
        binding.includeProducts.itemDes.tvYouhuiquanValue.text = "¥${item.couponAmount}"
        binding.includeProducts.itemDes.tvTotalYouhuiValue.text = "¥${item.couponAmount}"
        binding.includeProducts.itemDes.tvTotalValue.text = "¥${item.totalAmount}"

        binding.includeMessages.tvPeopleDes.text = ""
        binding.includeMessages.tvTimeDes.text = item.deliveryTime
        binding.includeMessages.tvAddressDes.text = item.receiverDetailAddress
        binding.includeMessages.tvAddressDes.text = "${item.receiverDetailAddress} \n ${item.receiverName} ${item.receiverPhone}"
        if(item.disTakeout.type){
            binding.includeMessages.tvTypeDes.text = "趣鲜蜂专送"
        }else{
            binding.includeMessages.tvTypeDes.text = ""
        }
        binding.includeMessages.tvOrdernumDes.text = item.orderSn
        binding.includeMessages.tvPayTypeDes.text = if(item.payType == 1) "米粒支付" else ""
        binding.includeMessages.tvPayTimeDes.text = UIUtils.getNomalTime(item.createTime)
        binding.includeMessages.tvBeizhuDes.text = item.note
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