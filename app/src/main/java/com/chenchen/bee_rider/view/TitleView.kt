package com.chenchen.bee_rider.view

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.chenchen.bee_rider.R
import com.gyf.immersionbar.ImmersionBar

/**
 * 创建时间：2022/1/15
 * @Author： 陈陈陈
 * 功能描述：TitleBar
 */
class TitleView : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        initViews(context,attrs)
    }
    private var back :ImageView? = null
    var right :ImageView? = null
    private var title :TextView? = null
    private var clTitleView : ConstraintLayout? = null
    private var colorType :Int = 0

    private fun initViews(context: Context, attrs: AttributeSet?) {
        val view = inflate(context, R.layout.title_view, this)
        val statusBar = view.findViewById<View>(R.id.status_bar)
        if(context is Activity){
            statusBar.layoutParams.height = ImmersionBar.getStatusBarHeight(context)
        }
        clTitleView = view.findViewById(R.id.cl_title_view)
        clTitleView?.background?.alpha = 0

        back = view.findViewById(R.id.iv_back)
        back?.setOnClickListener {
            if(context is Activity){
                val findNavController = context.findNavController(R.id.nav_host_fragment)
                findNavController.navigateUp()
            }
        }
        title = view.findViewById(R.id.tv_title)
        right = view.findViewById(R.id.iv_right)

        val a: TypedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.TitleView
        )
        val titleText = a.getString(R.styleable.TitleView_title)
        colorType = a.getInt(R.styleable.TitleView_contentColor,0)
        val icon = a.getResourceId(R.styleable.TitleView_rightIcon,-1)

        title?.text = titleText

        if(icon != -1){
            right?.visibility = View.VISIBLE
            right?.setImageResource(icon)
        }else{
            right?.visibility = View.GONE
        }
        setContentColor(colorType)
    }

    /**
     * 设置返回箭头 和 标题的颜色
     * int: 0白色；1黑色
     */
    fun setContentColor(int: Int){
        colorType = int
        if(int == 0){
            back?.setImageResource(R.drawable.icon_back_bai)
            title?.setTextColor(resources.getColor(R.color.white))
        }else{
            back?.setImageResource(R.drawable.icon_back_anse)
            title?.setTextColor(resources.getColor(R.color.color_222222))
        }
    }

    /**
     * 设置背景色透明度
     * alpha: 0 - 255
     */
    fun setBackgroundAlpha(alpha:Int){
        clTitleView?.background?.alpha = alpha
    }

    /**
     * 设置标题
     */
    fun setTitleText(text:String){
        title?.text = text
    }
}