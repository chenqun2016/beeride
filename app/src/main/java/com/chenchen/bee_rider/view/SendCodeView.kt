package com.chenchen.bee_rider.view

import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.chenchen.bee_rider.R
import com.chenchen.bee_rider.utils.countDownCoroutines
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope

/**
 * @author 陈陈陈
 */
class SendCodeView : FrameLayout, View.OnClickListener {
    private var tv_getcode: TextView? = null
    private var mListener: MyOnClickListener? = null
    private var mIsWorking = false
    private var i = 0
    private val clickableColor = R.color.color_3e7dfb
    private val unClickableColor = R.color.color_ccc

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context?) : super(context!!) {}

    private fun init(context: Context, attrs: AttributeSet?) {
        inflate(context, R.layout.view_code, this)
        tv_getcode = findViewById<View>(R.id.tv_getcode) as TextView
        tv_getcode!!.setOnClickListener(this)
    }

    /**
     * 初始化   必须调用
     *
     * @param listener
     */
    fun initDatas(listener: MyOnClickListener?) {
        mListener = listener
    }



    override fun onClick(view: View) {
        if (null == mListener) {
            return
        }
        doGetCode()
    }

    private fun doGetCode() {
        mIsWorking = true
        setCodeState(false)
        sendCode()
    }

    private fun sendCode() {
        //TODO 请求网络
        val phone = mListener!!.onGetPhoneNum()
        onHttpNext("")
        //        onHttpError();
    }

    private fun onHttpNext(s: String) {
        mListener!!.onSuccess(s)
//        startThread()
        val scope : CoroutineScope = if(context is FragmentActivity){
            (context as FragmentActivity).lifecycleScope
        }else{
            GlobalScope
        }
        countDownCoroutines(60,onTick = {
            val strs = "剩余" + it + "s"
            val msp = SpannableString(strs)
            msp.setSpan(
                ForegroundColorSpan(resources.getColor(clickableColor)), 2,
                strs.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE
            )
            tv_getcode!!.text = msp
        },onFinish = {
            setCodeState(true)
            tv_getcode!!.text = "重新发送"
            mIsWorking = false
        },scope)
    }

    private fun onHttpError() {
        mListener!!.onFailure("")
        mIsWorking = false
        setCodeState(true)
    }

    //设置短信验证码的可点击状态，颜色
    private fun setCodeState(enable: Boolean) {
        tv_getcode!!.isEnabled = enable
        tv_getcode!!.setTextColor(
            if (enable) resources.getColor(clickableColor) else resources.getColor(
                unClickableColor
            )
        )
    }

    interface MyOnClickListener {
        fun onGetPhoneNum(): String
        fun onSuccess(t: String?)
        fun onFailure(t: String?)
    }
}