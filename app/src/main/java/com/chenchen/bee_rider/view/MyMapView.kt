package com.chenchen.bee_rider.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import com.amap.api.maps.AMapOptions
import com.amap.api.maps.TextureMapView

/**
 * 创建人：进京赶考
 * 创建时间：2020/11/20  15：30
 * 描述：
 */
class MyMapView : TextureMapView {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attributeSet: AttributeSet?) : super(context, attributeSet) {}
    constructor(context: Context?, attributeSet: AttributeSet?, i: Int) : super(
        context,
        attributeSet,
        i
    ) {
    }

    constructor(context: Context?, aMapOptions: AMapOptions?) : super(context, aMapOptions) {}

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        parent.requestDisallowInterceptTouchEvent(true)
        return super.dispatchTouchEvent(ev)
    }
}