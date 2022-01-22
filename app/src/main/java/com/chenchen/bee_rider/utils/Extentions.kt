package com.chenchen.bee_rider.utils

import android.view.View
import android.view.ViewGroup
import com.chenchen.base.utils.d
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

fun View.startAlphaAnim(show:Boolean){
    val alpha = if(show) 1 else 0
    this.animate().alpha(alpha.toFloat()).setDuration(400).start()
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

