package com.chenchen.bee_rider.utils

import com.google.gson.Gson
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

