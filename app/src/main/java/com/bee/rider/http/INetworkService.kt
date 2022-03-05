package com.bee.rider.http

import com.bee.rider.bean.AppUpdateInfoBean
import com.bee.rider.bean.LoginBean
import com.bee.rider.bean.OrderListBean
import com.chenchen.base.network.base.BaseResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface INetworkService {

    @POST("app-dis-horseman/login")
    suspend fun login(@Body body: RequestBody?): BaseResponse<LoginBean>

    @POST("app-dis-horseman-takeout/list")
    suspend fun homeList(@Body body: RequestBody?): BaseResponse<OrderListBean>

    @POST("app-sys/appUpdateInfo")
    suspend fun appUpdateInfo(@Body body: RequestBody?): BaseResponse<AppUpdateInfoBean>
}