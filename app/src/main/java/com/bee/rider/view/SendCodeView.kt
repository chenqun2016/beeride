package com.bee.rider.view

import android.content.Context
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import com.bee.rider.R
import com.bee.rider.http.LoginApi
import com.bee.rider.utils.countDownCoroutines

/**
 * @author 陈陈陈
 */
class SendCodeView : FrameLayout, View.OnClickListener {
    private var mPhoneText: TextView? = null
    private var mcodeText: TextView? = null
    private lateinit var lifecycle: Lifecycle

    private var tv_getcode: TextView? = null
    private var mIsWorking = false
    private var i = 0
    private val clickableColor = R.color.color_3e7dfb
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
        tv_getcode = findViewById<View>(R.id.tv_getcode) as TextView
        tv_getcode!!.setOnClickListener(this)
    }

    /**
     * 监听手机号空间格式是否正确
     */
    val textWatcher: TextWatcher = object : TextWatcher {
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

    /**
     * 初始化   必须调用
     *
     * @param listener
     */
    fun initDatas(phoneText: TextView, codeText: TextView, lifecycle: Lifecycle) {
        this.mPhoneText = phoneText
        this.mcodeText = codeText
        setCodeState(false)
        mPhoneText?.addTextChangedListener(textWatcher)
        this.lifecycle = lifecycle
        lifecycle.addObserver(lifeObserver)
    }

    private val lifeObserver: LifecycleEventObserver = LifecycleEventObserver { source, event ->
        if (event == Lifecycle.Event.ON_DESTROY) {
            mPhoneText?.removeTextChangedListener(textWatcher)
            mPhoneText = null
            mcodeText = null
        }
    }

    override fun onClick(view: View) {
        doGetCode()
    }

    private fun doGetCode() {
        hasSendCode = true
        mIsWorking = true
        setCodeState(false)
        mcodeText?.text = ""
        sendCode()
    }

    private fun sendCode() {
        //TODO 请求网络
        if (null == mPhoneText) {
            return
        }
        val phone = mPhoneText?.text.toString()
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
            tv_getcode!!.text = msp
        }, onFinish = {
            setCodeState(true)
            tv_getcode!!.text = "重新发送"
            mIsWorking = false
        }, lifecycle.coroutineScope)
    }

    private fun onHttpError() {
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
}