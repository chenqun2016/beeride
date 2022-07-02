package com.bee.rider.http

import com.bee.rider.bean.*
import com.chenchen.base.network.base.BaseResponse
import okhttp3.RequestBody
import retrofit2.http.*

interface INetworkService {

    @POST("orderTakeout/getTakeoutOrder")
    suspend fun homeList(@Query("pageNum") pageNum:Int, @Query("pageSize") pageSize:Int, @Body body: RequestBody?): BaseResponse<OrderListBean>

    @POST("orderTakeout/historyList")
    suspend fun historyList(@Query("pageNum") pageNum:Int, @Query("pageSize") pageSize:Int, @Body body: RequestBody?): BaseResponse<OrderListBean>

    @GET("orderTakeout/get/{takeoutId}")
    suspend fun orderDetail(@Path("takeoutId") takeoutId:String): BaseResponse<OrderDetailBean>

    //骑手接单/取货/送达
    @POST("disOrder/horsemanAcceptOrder")
    suspend fun initiativeCreate(@Body body: RequestBody?): BaseResponse<Any>
    @POST("app-sys/appUpdateInfo")
    suspend fun appUpdateInfo(@Body body: RequestBody?): BaseResponse<AppUpdateInfoBean>

    @GET("orderTakeout/getOperateHistory/{orderId}")
    suspend fun getOperateHistory(@Path("orderId") orderId:String?): BaseResponse<List<OrderDetailTraceBean>>

}