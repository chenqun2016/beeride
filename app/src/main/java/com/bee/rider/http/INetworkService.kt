package com.bee.rider.http

import com.bee.rider.bean.AppUpdateInfoBean
import com.bee.rider.bean.LoginBean
import com.bee.rider.bean.OrderDetailBean
import com.bee.rider.bean.OrderListBean
import com.chenchen.base.network.base.BaseResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface INetworkService {

    @POST("app-dis-horseman/login")
    suspend fun login(@Body body: RequestBody?): BaseResponse<LoginBean>

    @POST("app-dis-horseman-takeout/list")
    suspend fun homeList(@Body body: RequestBody?): BaseResponse<OrderListBean>

    @GET("app-dis-horseman-takeout/get/{takeoutId}")
    suspend fun orderDetail(@Path("takeoutId") takeoutId:String): BaseResponse<OrderDetailBean>


    @POST("dis-horseman-takeout/initiativeCreate")
    suspend fun initiativeCreate(@Body body: RequestBody?): BaseResponse<Any>
    @POST("app-sys/appUpdateInfo")
    suspend fun appUpdateInfo(@Body body: RequestBody?): BaseResponse<AppUpdateInfoBean>
}