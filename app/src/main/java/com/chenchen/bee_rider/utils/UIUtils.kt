package com.chenchen.bee_rider.utils

import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.chenchen.bee_rider.R
import com.gyf.immersionbar.ImmersionBar

/**
 * 创建时间：2022/1/8
 * @Author： 陈陈陈
 * 功能描述：
 */
object  UIUtils {
    fun setGradientDrawable(fragment:Fragment, statusBar: View?, bg:ImageView,color:Int){
        if(null != statusBar){
            val layoutParams = statusBar.layoutParams
            layoutParams?.height = ImmersionBar.getStatusBarHeight(fragment)
        }
        val colors = intArrayOf(
            fragment.resources.getColor(color),
            fragment.resources.getColor(color),
            fragment.resources.getColor(color),
            fragment.resources.getColor(color),
            fragment.resources.getColor(R.color.transparent))
        val gradientDrawable = GradientDrawable()
        gradientDrawable.shape = GradientDrawable.RECTANGLE
        gradientDrawable.colors = colors //添加颜色组
        gradientDrawable.gradientType = GradientDrawable.LINEAR_GRADIENT //设置线性渐变
        gradientDrawable.orientation = GradientDrawable.Orientation.TOP_BOTTOM //设置渐变方向
        bg.scaleType = ImageView.ScaleType.FIT_XY
        bg.setImageDrawable(gradientDrawable)
    }
}