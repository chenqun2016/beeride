package com.chenchen.bee_rider.http

import com.chenchen.base.network.base.BaseResponse
import okhttp3.RequestBody
import retrofit2.http.*

interface INetworkService {

    @POST("auth/login")
    suspend fun login(@Body body: RequestBody?): BaseResponse<Any>

}