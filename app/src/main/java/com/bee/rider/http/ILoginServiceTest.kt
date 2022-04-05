package com.bee.rider.http

import com.bee.rider.bean.LoginBean
import com.chenchen.base.network.base.BaseResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * 创建时间：2022/4/5
 * 编写人： 陈陈陈
 * 功能描述：
 */
interface ILoginServiceTest {
    @POST("app-dis-horseman/login")
    suspend fun login2(@Body body: RequestBody?): BaseResponse<LoginBean>
}