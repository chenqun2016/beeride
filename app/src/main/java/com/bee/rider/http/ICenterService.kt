package com.bee.rider.http

import com.bee.rider.bean.UserBean
import com.chenchen.base.network.base.BaseResponse
import retrofit2.http.GET

/**
 * 创建时间：2022/6/25
 * 编写人： 陈陈陈
 * 功能描述：
 */
interface ICenterService {
    @GET("app-dis-horseman/getDetail/11")
    suspend fun getDetail(): BaseResponse<UserBean>
}