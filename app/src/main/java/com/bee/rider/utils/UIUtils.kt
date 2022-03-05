package com.bee.rider.utils

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.PopUpToBuilder
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.bee.rider.Constants
import com.bee.rider.R
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
        if(null != fragment.context){
            val colors = intArrayOf(
                ContextCompat.getColor(fragment.requireContext(),color),
                ContextCompat.getColor(fragment.requireContext(),color),
                ContextCompat.getColor(fragment.requireContext(),color),
                ContextCompat.getColor(fragment.requireContext(),color),
                ContextCompat.getColor(fragment.requireContext(),R.color.transparent))
            val gradientDrawable = GradientDrawable()
            gradientDrawable.shape = GradientDrawable.RECTANGLE
            gradientDrawable.colors = colors //添加颜色组
            gradientDrawable.gradientType = GradientDrawable.LINEAR_GRADIENT //设置线性渐变
            gradientDrawable.orientation = GradientDrawable.Orientation.TOP_BOTTOM //设置渐变方向
            bg.scaleType = ImageView.ScaleType.FIT_XY
            bg.setImageDrawable(gradientDrawable)
        }
    }

    fun setGradientDrawable(bg:View,fromColor:Int,toColor:Int){
        if(null != bg.context){
            val colors = intArrayOf(
                ContextCompat.getColor(bg.context,fromColor),
                ContextCompat.getColor(bg.context,toColor))
            val gradientDrawable = GradientDrawable()
            gradientDrawable.shape = GradientDrawable.RECTANGLE
            gradientDrawable.colors = colors //添加颜色组
            gradientDrawable.gradientType = GradientDrawable.LINEAR_GRADIENT //设置线性渐变
            gradientDrawable.orientation = GradientDrawable.Orientation.LEFT_RIGHT //设置渐变方向
            bg.background = gradientDrawable
        }
    }

    /**
     * Fragment 切换动画
     */
    fun getNavOptions(popUpToId: Int? = null): NavOptions {
        if (null == popUpToId){
            return options
        }
        return navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
            popUpTo(popUpToId, object : (PopUpToBuilder) -> Unit {
                override fun invoke(p1: PopUpToBuilder) {
                    p1.inclusive = true
                }
            })
        }
    }

    /**
     * 设置协议
     */
    fun setXieYiText(fragment: Fragment,view:TextView){
        try {
            if(null == fragment.context){
                return
            }
            val str = "已阅读并同意"
            val str1 = "《用户服务协议》"
            val str2 = "和"
            val str3 = "《隐私政策》"
            val msp = SpannableString(str + str1 + str2 + str3)
            msp.setSpan(object : ClickableSpan() {
                override fun onClick(view: View) {
                    val bundle = Bundle()
                    bundle.putString("url", Constants.agreement_privacy)
                    fragment.findNavController().navigate(R.id.common_web_dest,bundle, options)
                }
                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                }
            }, str.length, str.length + str1.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            msp.setSpan(object : ClickableSpan() {
                override fun onClick(view: View) {
                    val bundle = Bundle()
                    bundle.putString("url", Constants.agreement_privacy)
                    fragment.findNavController().navigate(R.id.common_web_dest,bundle, options)
                }
                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                }
            }, str.length + str1.length + str2.length, msp.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            msp.setSpan(object :  ForegroundColorSpan(ContextCompat.getColor(fragment.requireContext(),R.color.colorPrimary)){}, str.length, str.length + str1.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            msp.setSpan(object :  ForegroundColorSpan(ContextCompat.getColor(fragment.requireContext(),R.color.colorPrimary)){}, str.length + str1.length + str2.length, msp.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            view.movementMethod = LinkMovementMethod.getInstance()
            view.highlightColor = Color.TRANSPARENT
            view.text = msp
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setButtonStatus(button:View,enable:Boolean) {
        if (enable) {
            button.isEnabled = true
            button.setBackgroundResource(R.drawable.btn_gradient_yellow_round)
        } else {
            button.isEnabled = false
            button.setBackgroundResource(R.drawable.btn_gradient_grey_round)
        }
    }
}