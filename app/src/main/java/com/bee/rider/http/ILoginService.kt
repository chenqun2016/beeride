package com.bee.rider.http

import com.chenchen.base.network.base.BaseResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * 创建时间：2022/4/2
 * 编写人： 陈陈陈
 * 功能描述：
 *
 *
/api-uaa/member/login/password
/api-uaa/member/login/smscode
/api-uaa/member/register
/api-uaa/member/resetPassword
/api-uaa/validata/code/{client_id}
/api-uaa/validata/smsCode/{mobile}
 */
interface ILoginService {
    @POST("horseman/login/password")
    suspend fun login(@Body body: RequestBody?): BaseResponse<String>

    @POST("horseman/login/smscode")
    suspend fun loginSmscode(@Body body: RequestBody?): BaseResponse<String>

    @POST("horseman/resetPassword")
    suspend fun resetPassword(@Body body: RequestBody?): BaseResponse<String>

    @GET("validata/smsCode/{mobile}")
    suspend fun smsCode(@Path("mobile") mobile:String): BaseResponse<String>

    @GET("validata/checkSmsCode/{mobile}/{code}")
    suspend fun checkSmsCode(@Path("mobile") mobile:String,@Path("code") code:String): BaseResponse<String?>
}