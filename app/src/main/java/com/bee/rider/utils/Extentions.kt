package com.bee.rider.utils

import android.animation.Animator
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.chenchen.base.utils.d
import com.bee.rider.R
import com.google.gson.Gson
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

/**
 * 创建时间：2021/12/20
 * @Author： 陈陈陈
 * 功能描述：扩展函数类
 */


/**
 * 实体类转网络请求体
 */
fun Any.toApiBody() :RequestBody?{
    return Gson().toJson(this)
        .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
}

fun ViewGroup.setRoundConner(radius:Int,radiusSide:Int){
    ViewHelper.setViewOutline(this,radius,radiusSide)
}

fun TextView.setButtonClickableBy(vararg args: EditText){
    isEnabled = false
    setBackgroundResource(R.drawable.btn_gradient_grey_round)
    for (i in args.indices) {
        args[i].addTextChangedListener {
            var enable = true
            for (j in args.indices){
                enable = enable && !args[j].text.isNullOrEmpty()
            }
            UIUtils.setButtonStatus(this,enable)
        }
    }
}

fun View.startAlphaAnim(show:Boolean,isVisibleOrGone:Boolean = false){
    val alpha = if(show) 1 else 0
    this.animate().alpha(alpha.toFloat()).setDuration(400).setListener(object :Animator.AnimatorListener{
        override fun onAnimationStart(animation: Animator?) {
            if(isVisibleOrGone && show){
                visibility = View.VISIBLE
            }
        }
        override fun onAnimationEnd(animation: Animator?) {
            if(isVisibleOrGone && !show){
                visibility = View.GONE
            }
        }
        override fun onAnimationCancel(animation: Animator?) {
        }

        override fun onAnimationRepeat(animation: Animator?) {
        }
    }).start()
}

/**
 * 倒计时
 */
fun countDownCoroutines(total:Int,onTick:(Int)->Unit,onFinish:()->Unit,
                        scope: CoroutineScope = GlobalScope
): Job {
    return flow{
        for (i in total downTo 0){
            emit(i)
            delay(1000)
        }
    }.flowOn(Dispatchers.Default)
        .onCompletion {
            onFinish.invoke()
            d("countDownCoroutines","onCompletion")
        }
        .onEach {
            onTick.invoke(it)
            d("countDownCoroutines", "onEach==$it")
        }
        .flowOn(Dispatchers.Main)
        .launchIn(scope)
}
