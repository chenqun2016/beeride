package com.chenchen.base.network.interceptor

import android.os.Build
import com.chenchen.base.constants.HttpConstants
import com.chenchen.base.utils.MMKVUtils
import okhttp3.Interceptor
import okhttp3.Response

class CommonRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        // 这里添加公共请求头
        builder.addHeader("brand", Build.BRAND)
        builder.addHeader("model", Build.MODEL)

        builder.addHeader("x-user-header", "test1")
        //TODO token
        builder.addHeader("Authorization", MMKVUtils.getString(HttpConstants.TOKEN,""))
        builder.addHeader("horsemanId", MMKVUtils.getString(HttpConstants.HORSEMANID,""))

        return chain.proceed(builder.build())
    }
}