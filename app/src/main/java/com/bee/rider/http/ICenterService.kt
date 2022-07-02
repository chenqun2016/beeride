package com.bee.rider.http

import com.bee.rider.bean.UserBean
import com.chenchen.base.network.base.BaseResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * 创建时间：2022/6/25
 * 编写人： 陈陈陈
 * 功能描述：
 */
interface ICenterService {
    @GET("app-dis-horseman/getDetail/11")
    suspend fun getDetail(): BaseResponse<UserBean>

    @POST("app-dis-horseman/updateWorkStatus")
    suspend fun updateWorkStatus(@Body body: RequestBody?): BaseResponse<Any>
}