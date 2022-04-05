package com.bee.rider.http

import com.bee.rider.bean.*
import com.chenchen.base.network.base.BaseResponse
import okhttp3.RequestBody
import retrofit2.http.*

interface INetworkService {

    @POST("app-dis-horseman-takeout/list")
    suspend fun homeList(@Query("pageNum") pageNum:Int, @Query("pageSize") pageSize:Int, @Body body: RequestBody?): BaseResponse<OrderListBean>

    @POST("app-dis-horseman-takeout/historyList")
    suspend fun historyList(@Query("pageNum") pageNum:Int, @Query("pageSize") pageSize:Int, @Body body: RequestBody?): BaseResponse<OrderListBean>

    @GET("app-dis-horseman-takeout/get/{takeoutId}")
    suspend fun orderDetail(@Path("takeoutId") takeoutId:String): BaseResponse<OrderDetailBean>


    @POST("dis-horseman-takeout/initiativeCreate")
    suspend fun initiativeCreate(@Body body: RequestBody?): BaseResponse<Any>
    @POST("app-sys/appUpdateInfo")
    suspend fun appUpdateInfo(@Body body: RequestBody?): BaseResponse<AppUpdateInfoBean>

    @GET("app-dis-horseman-takeout/getOperateHistory/{orderId}")
    suspend fun getOperateHistory(@Path("orderId") orderId:String?): BaseResponse<List<OrderDetailTraceBean>>

}