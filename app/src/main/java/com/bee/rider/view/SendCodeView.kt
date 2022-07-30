package com.bee.rider.view

import android.content.Context
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.coroutineScope
import com.bee.rider.R
import com.bee.rider.http.LoginApi
import com.bee.rider.utils.countDownCoroutines

/**
 * @author 陈陈陈
 *
 * 验证码 控件
 */
class SendCodeView : FrameLayout, View.OnClickListener {
    private var mPhoneText: TextView? = null
    private var mCodeText: TextView? = null
    private lateinit var lifecycle: Lifecycle

    var tvGetCode: TextView? = null
    private var mIsWorking = false
    private var i = 0
    private val clickableColor = R.color.color_FF6100
    private val unClickableColor = R.color.color_ccc

    /**
     * 是否发送过验证码
     */
    var hasSendCode = false

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
        tvGetCode = findViewById<View>(R.id.tv_getcode) as TextView
        tvGetCode!!.setOnClickListener(this)
    }

    /**
     * 初始化   必须调用
     * @param phoneText：手机号 控件
     * @param codeText：验证码 控件
     * @param lifecycle：Activity或者Fragment的 lifecycle
     */
    fun initDatas(phoneText: TextView, codeText: TextView, lifecycle: Lifecycle) {
        this.mPhoneText = phoneText
        this.mCodeText = codeText
        setCodeState(false)
        mPhoneText?.addTextChangedListener(textWatcher)
        this.lifecycle = lifecycle
        lifecycle.addObserver(lifeObserver)
    }

    private val lifeObserver: LifecycleEventObserver = LifecycleEventObserver { source, event ->
        if (event == Lifecycle.Event.ON_DESTROY) {
            mPhoneText?.removeTextChangedListener(textWatcher)
            mPhoneText = null
            mCodeText = null
        }
    }

    /**
     * 监听手机号空间格式是否正确
     */
    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(it: CharSequence?, start: Int, before: Int, count: Int) {
            if (null != it && it.toString().isNotEmpty() && it.toString().length == 11) {
                setCodeState(true)
            } else {
                setCodeState(false)
            }
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

    override fun onClick(view: View) {
        doGetCode()
    }

    private fun doGetCode() {
        hasSendCode = true
        mIsWorking = true
        setCodeState(false)
        mCodeText?.text = ""
        sendCode()
    }

    private fun sendCode() {
        if (null == mPhoneText) {
            return
        }
        val phone = (mPhoneText?.tag ?: mPhoneText?.text).toString()
        lifecycle.coroutineScope.launchWhenResumed {
            val result = LoginApi.smsCode(phone)
            if (result.isSuccess) {
                onHttpNext("")
            } else {
                onHttpError()
            }
        }
    }

    private fun onHttpNext(s: String) {
        countDownCoroutines(60, onTick = {
            val strs = "剩余" + it + "s"
            val msp = SpannableString(strs)
            msp.setSpan(
                ForegroundColorSpan(resources.getColor(clickableColor)), 2,
                strs.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE
            )
            tvGetCode!!.text = msp
        }, onFinish = {
            setCodeState(true)
            tvGetCode!!.text = "重新发送"
            mIsWorking = false
        }, lifecycle.coroutineScope)
    }

    private fun onHttpError() {
        mIsWorking = false
        setCodeState(true)
    }

    /**
     * 设置Textview 是否可点击，颜色变换
     */
    private fun setCodeState(enable: Boolean) {
        tvGetCode!!.isEnabled = enable
        tvGetCode!!.setTextColor(
            if (enable) resources.getColor(clickableColor) else resources.getColor(
                unClickableColor
            )
        )
    }
}