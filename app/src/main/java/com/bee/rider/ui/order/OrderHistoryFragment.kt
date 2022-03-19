package com.bee.rider.ui.order

import android.animation.Animator
import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.LinearInterpolator
import android.widget.PopupWindow
import android.widget.RadioButton
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.chenchen.base.base.BaseFragment
import com.bee.rider.Constants
import com.bee.rider.R
import com.bee.rider.databinding.FragmentOrderHistoryBinding
import com.bee.rider.ui.home.OrderListFragment
import com.bee.rider.utils.UIUtils
import com.bee.rider.view.RadioGroupPlus
import com.gyf.immersionbar.ImmersionBar
import java.text.ParseException
import java.util.*

/**
 * 创建时间：2022/1/8
 * @Author： 陈陈陈
 * 功能描述：
 */
class OrderHistoryFragment : BaseFragment<FragmentOrderHistoryBinding>() {
    var popupWindow: PopupWindow? = null
    var endDate: String? = null
    var beginDate: String? = null
    var transactionType: String? = null

    private var fragment: OrderListFragment? = null

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentOrderHistoryBinding {
        return FragmentOrderHistoryBinding.inflate(inflater, container, false)
    }
    override fun initOnce(savedInstanceState: Bundle?) {}

    override fun initViews(savedInstanceState: Bundle?) {
        UIUtils.setGradientDrawable(this, null, binding.ivBg, R.color.color_FF6200)
        if (savedInstanceState == null && fragment == null) {
            fragment = OrderListFragment.newInstance(OrderListFragment.TYPE_HISTORY)
            //TODO 默认选中时间段
            fragment?.reflushDatas(beginDate,endDate)
            childFragmentManager.beginTransaction().setReorderingAllowed(true)
                .add(R.id.fcv, fragment!!, "OrderListFragment_history").commitNowAllowingStateLoss()
        }

        binding.titleView.right?.setOnClickListener {
            showShaixuan()
        }
    }

    private fun showShaixuan() {
        if (null == popupWindow) {
            val mMenuView =
                LayoutInflater.from(context).inflate(R.layout.popop_window_trade_list, null)
            popupWindow = PopupWindow(
                mMenuView,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            val rp_1: RadioGroupPlus = mMenuView.findViewById(R.id.rp_1)
            val tv_time_left = mMenuView.findViewById<TextView>(R.id.tv_time_left)
            val tv_time_right = mMenuView.findViewById<TextView>(R.id.tv_time_right)
            rp_1.setOnCheckedChangeListener(object : RadioGroupPlus.OnCheckedChangeListener {
                override fun onCheckedChanged(group: RadioGroupPlus?, checkedId: Int) {
                    val calendar = Calendar.getInstance()
                    val nowString: String = Constants.sdfLong2.format(calendar.time)
                    tv_time_right.text = nowString
                    val passString: String
                    when (checkedId) {
                        R.id.rb_1 -> {
                            calendar[Calendar.YEAR] = calendar[Calendar.YEAR] - 1
                            passString = Constants.sdfLong2.format(calendar.time)
                            tv_time_left.text = passString
                        }
                        R.id.rb_2 -> {
                            calendar[Calendar.MONTH] = calendar[Calendar.MONTH] - 6
                            passString = Constants.sdfLong2.format(calendar.time)
                            tv_time_left.text = passString
                        }
                        R.id.rb_3 -> {
                            calendar[Calendar.MONTH] = calendar[Calendar.MONTH] - 3
                            passString = Constants.sdfLong2.format(calendar.time)
                            tv_time_left.text = passString
                        }
                        else -> {
                        }
                    }
                }
            })
            val rp_2: RadioGroupPlus = mMenuView.findViewById(R.id.rp_2)
            rp_2.setOnCheckedChangeListener(object :RadioGroupPlus.OnCheckedChangeListener {
                override fun onCheckedChanged(group: RadioGroupPlus?, checkedId: Int) {

                }
            })
            rp_1.check(R.id.rb_1)
            rp_2.check(R.id.rb_11)
            val btn_1 = mMenuView.findViewById<View>(R.id.btn_1)
            btn_1.setOnClickListener {
                rp_1.check(R.id.rb_1)
                rp_2.check(R.id.rb_11)
            }
            val btn_2 = mMenuView.findViewById<View>(R.id.btn_2)
            btn_2.setOnClickListener {
                popupWindow!!.dismiss()
                beginDate = tv_time_left.text.toString()
                endDate = tv_time_right.text.toString()
                transactionType = when (rp_2.checkedRadioButtonId) {
                    R.id.rb_11 -> "A"
                    R.id.rb_22 -> "T"
                    R.id.rb_111 -> "I"
                    R.id.rb_222 -> "S"
                    else -> "A"
                }
                val checkedRadioButtonId: Int = rp_1.checkedRadioButtonId
                val checkedBt = mMenuView.findViewById<RadioButton>(checkedRadioButtonId)
                binding.titleView.setTitleText(checkedBt.text.toString() + "历史订单")
                if(!TextUtils.isEmpty(beginDate) && !TextUtils.isEmpty(endDate)){
                    //TODO 时间格式
                    fragment?.reflushDatas(beginDate!!,endDate!!)
                }
            }
            tv_time_left.setOnClickListener { showBirthdayDialog(tv_time_left) }
            tv_time_right.setOnClickListener { showBirthdayDialog(tv_time_right) }
            popupWindow!!.animationStyle = R.style.AnimTools
            //实例化一个ColorDrawable颜色为半透明0xb0000000
            val dw = ColorDrawable(0x00000000) //
            //        //设置SelectPicPopupWindow弹出窗体的背景
            popupWindow!!.setBackgroundDrawable(dw)
            popupWindow!!.isFocusable = true
            popupWindow!!.isOutsideTouchable = true
            popupWindow!!.setOnDismissListener { close() }
        }
        popupWindow!!.showAsDropDown(binding.titleView, 0, 0)
        show()
    }


    private var pvTime: TimePickerView? = null

    //    //时间选择器
    private fun showBirthdayDialog(view: TextView) {

        //时间选择器
        val instance = Calendar.getInstance()
        instance.add(Calendar.YEAR, -100)
        pvTime = TimePickerBuilder(context, OnTimeSelectListener { date, v -> //选中事件回调
            var date = date
            if (date.time > System.currentTimeMillis()) {
                date = Date()
            }
            view.text = getDateLong(date.time)
        })
            .setType(booleanArrayOf(true, true, true, false, false, false)) //分别对应 年月日时分秒，默认全部显示
            .setLabel("年", "月", "日", "时", "分", "秒")
            .setCancelText("取消") //取消按钮文字
            .setSubmitText("确定") //确认按钮文字
            .setContentTextSize(15) //滚轮文字大小
            .setTitleSize(17) //标题文字大小
            .setTitleText("选择日期") //标题文字
            .setOutSideCancelable(true) //点击屏幕，点在控件外部范围时，是否取消显示
            .isCyclic(false) //是否循环滚动
            .setTitleColor(resources.getColor(R.color.color_282525)) //标题文字颜色
            .setSubmitColor(resources.getColor(R.color.color_3e7dfb)) //确定按钮文字颜色
            .setCancelColor(resources.getColor(R.color.color_7C7877)) //取消按钮文字颜色
            .setTitleBgColor(Color.WHITE) //标题背景颜色 Night mode
            .setBgColor(Color.WHITE) //滚轮背景颜色 Night mode
            .setRangDate(instance, Calendar.getInstance()) //默认是1900-2100年
            .setLineSpacingMultiplier(3.0f)
            .setLayoutRes(
                R.layout.pickerview_custom_time_dialog
            ) { v ->
                val tvSubmit = v.findViewById<View>(R.id.tv_finish) as TextView
                val tv_cancel = v.findViewById<View>(R.id.tv_cancel) as TextView
                tvSubmit.setOnClickListener {
                    pvTime!!.returnData()
                    pvTime!!.dismiss()
                }
                tv_cancel.setOnClickListener { pvTime!!.dismiss() }
            }
            .isDialog(true)
            .build()
        val time = view.text.toString()
        val current = Calendar.getInstance()
        try {
            val parse = Constants.sdfLong2.parse(time)
            current.time = parse
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        pvTime?.setDate(current) //注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime?.show()
    }

    private fun getDateLong(d: Long): String? {
        val date = Date(d)
        var nowDate: String? = ""
        return try {
            nowDate = Constants.sdfLong2.format(date)
            nowDate
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    //弹窗popwindow 的时候，背景同步变暗。
    private var alphaAnimation1: ValueAnimator? = null
    private var alphaAnimation2: ValueAnimator? = null

    private fun show() {
        if (null != alphaAnimation2 && alphaAnimation2?.isRunning == true) {
            alphaAnimation2?.end()
        }
        binding.background.visibility = View.VISIBLE
        if (null == alphaAnimation1) {
            alphaAnimation1 = ValueAnimator.ofFloat(0f, 1f)
            alphaAnimation1?.duration = 200
            alphaAnimation1?.interpolator = LinearInterpolator()
            alphaAnimation1?.addUpdateListener(ValueAnimator.AnimatorUpdateListener { valueAnimator ->
                binding.background.alpha = valueAnimator.animatedValue as Float
            })
        }
        alphaAnimation1!!.start()
    }


    private fun close() {
        if (null != alphaAnimation1 && alphaAnimation1!!.isRunning) {
            alphaAnimation1!!.end()
        }
        if (null == alphaAnimation2) {
            alphaAnimation2 = ValueAnimator.ofFloat(1f, 0f)
            alphaAnimation2?.duration = 200
            alphaAnimation2?.interpolator = LinearInterpolator()
            alphaAnimation2?.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {}
                override fun onAnimationEnd(animator: Animator) {
                    binding.background.visibility = View.GONE
                    binding.background.alpha = 0f
                }

                override fun onAnimationCancel(animator: Animator) {
                    binding.background.visibility = View.GONE
                    binding.background.alpha = 0f
                }

                override fun onAnimationRepeat(animator: Animator) {}
            })
            alphaAnimation2?.addUpdateListener(ValueAnimator.AnimatorUpdateListener { valueAnimator ->
                binding.background.alpha = valueAnimator.animatedValue as Float
            })
        }
        alphaAnimation2!!.start()
    }

}