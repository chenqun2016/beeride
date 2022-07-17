package com.bee.rider.ui.order

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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
import com.blankj.utilcode.util.ObjectUtils
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
    AMap.OnInfoWindowClickListener, View.OnClickListener, View.OnLongClickListener {

    companion object{
        fun newInstance(takeoutId: String?,type:Int): OrderDetailTab1Fragment{
            val args = Bundle()
            args.putString(Constants.TAKEOUTID, takeoutId)
            args.putInt(Constants.TYPE, type)
            val fragment = OrderDetailTab1Fragment()
            fragment.arguments = args
            return fragment
        }
    }
    //0:新订单， 1：待取货   ，2：待送货
    private var mType:Int = 0
    private var mData :OrderDetailBean? = null
    private var productsAdapter :ProductsAdapter? = null

    private var aMap: AMap? = null

    override fun initOnce(savedInstanceState: Bundle?) {

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


    override fun onLongClick(v: View?): Boolean {
        when(v?.id){
            R.id.tv_accept -> {
                val status: Int = when (mType) {
                    0 -> 20
                    1 -> 30
                    2 -> 40
                    else -> 50
                }
                val param = InitiativeCreateParams(mData?.disTakeout?.id,mData?.disTakeout?.id,status)
                lifecycleScope.launch {
                    val it = NetworkApi.initiativeCreate(param)
                    if (it.isSuccess) {
                        mType++
                        val bean = it.getOrNull()
                        context?.let { it1 -> UIUtils.showAcceptButtomToast(mType, it1) }
                        UIUtils.setAccepeButtomTextByType(mType,binding.includeDetail.includeOrderItem.tvAccept)
                        if(mType in arrayOf(0,1,2)){
                            binding.includeDetail.includeOrderItem.tvAccept.visibility = View.VISIBLE
                        }else{
                            binding.includeDetail.includeOrderItem.tvAccept.visibility = View.GONE
                        }
                    }
                }
            }
        }
        return false
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_contact -> {
                if(ObjectUtils.isNotEmpty(mData?.shopStoreDetailVO?.contactMobile)){
                    startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mData?.shopStoreDetailVO?.contactMobile)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                }
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
        mType = arguments?.getInt(Constants.TYPE) ?: 0

        val layoutParams = binding.toolbar.layoutParams
        layoutParams.height = DisplayUtil.dip2px(context,40f)+ImmersionBar.getStatusBarHeight(this)
        binding.toolbar.layoutParams = layoutParams
        binding.toolbar.minimumHeight = layoutParams.height
        binding.includeProducts.products.layoutManager = LinearLayoutManager(context)
        productsAdapter = ProductsAdapter()
        binding.includeProducts.products.adapter = productsAdapter

        binding.includeDetail.includeOrderItem.tvContact.visibility = View.VISIBLE
        binding.includeMessages.tvCopy.setOnClickListener(this)
        binding.includeDetail.includeOrderItem.tvAccept.setOnLongClickListener(this)
        binding.includeDetail.includeOrderItem.tvContact.setOnClickListener(this)
        UIUtils.setAccepeButtomTextByType(mType,binding.includeDetail.includeOrderItem.tvAccept)
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


        val takeoutId = arguments?.getString(Constants.TAKEOUTID)

    }

    fun setDatas(item: OrderDetailBean) {
        mData = item
        binding.includeDetail.includeOrderItem.tvAccept.visibility = if(mType in arrayOf(0,1,2)) View.VISIBLE else View.GONE
//        binding.includeDetail.includeOrderItem.tvAccept.visibility = if(item.queryStatus in arrayOf(10,20,30)) View.VISIBLE else View.GONE
        binding.includeDetail.includeOrderItem.tvRight.text = "#${item.deliverySn}"
        binding.includeDetail.includeOrderItem.tvTime.text = "${UIUtils.getNomalTime2(item.disTakeout.expectedTime)}前送达"
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

        binding.includeProducts.itemDes.tvBaozhuangfeiValue.text =  "¥${item.packingFeeAmount?:0}"
        val freight = if(item.freightAmount == null || item.freightAmount==0) "免运费" else "¥${item.freightAmount}"
        binding.includeProducts.itemDes.tvPeisongfeiValue.text = freight
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
            binding.includeMessages.tvTypeDes.text = "趣鲜蜂专送"
        }
        binding.includeMessages.tvOrdernumDes.text = item.orderSn
        binding.includeMessages.tvPayTypeDes.text = if(item.payType == 1) "米粒支付" else "米粒支付"
        binding.includeMessages.tvPayTimeDes.text = UIUtils.getNomalTime(item.createTime)
        binding.includeMessages.tvBeizhuDes.text = item.note

        if(mType <= 10){
            binding.includeMessages.line4.visibility = View.GONE
            binding.includeMessages.tvPeople.visibility = View.GONE
            binding.includeMessages.tvPeopleDes.visibility = View.GONE
            binding.includeMessages.line1.visibility = View.GONE
            binding.includeMessages.tvTime.visibility = View.GONE
            binding.includeMessages.tvTimeDes.visibility = View.GONE
        }else{
            binding.includeMessages.line4.visibility = View.VISIBLE
            binding.includeMessages.tvPeople.visibility = View.VISIBLE
            binding.includeMessages.tvPeopleDes.visibility = View.VISIBLE
            binding.includeMessages.line1.visibility = View.VISIBLE
            binding.includeMessages.tvTime.visibility = View.VISIBLE
            binding.includeMessages.tvTimeDes.visibility = View.VISIBLE
        }
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