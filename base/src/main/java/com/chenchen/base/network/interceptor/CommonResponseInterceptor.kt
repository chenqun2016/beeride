package com.chenchen.base.network.interceptor

import com.chenchen.base.network.base.BaseNetworkApi
import com.chenchen.base.utils.d
import okhttp3.Interceptor
import okhttp3.Response
import java.nio.charset.Charset

class CommonResponseInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val startTime = System.currentTimeMillis()
        val request = chain.request()
        val response = chain.proceed(request)
        val body = response.body?.source()?.buffer?.clone()?.readString(Charset.forName("UTF-8"))
        d(BaseNetworkApi.TAG, "url=${request.url}, requestTime=${System.currentTimeMillis() - startTime}ms")
        return response
    }
}