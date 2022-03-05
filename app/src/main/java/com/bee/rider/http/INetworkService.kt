package com.bee.rider.http

import com.chenchen.base.network.base.BaseResponse
import com.bee.rider.bean.LoginBean
import okhttp3.RequestBody
import retrofit2.http.*

interface INetworkService {

    @POST("app-dis-horseman/login")
    suspend fun login(@Body body: RequestBody?): BaseResponse<LoginBean>

}