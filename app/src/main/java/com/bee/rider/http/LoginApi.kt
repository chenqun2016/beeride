package com.bee.rider.http

import com.bee.rider.Constants
import com.bee.rider.params.LoginParams
import com.bee.rider.params.ResetPasswordParams
import com.bee.rider.params.SmsCodeLoginParams
import com.bee.rider.utils.toApiBody
import com.chenchen.base.network.base.BaseNetworkApi

/**
 * 创建时间：2022/4/2
 * 编写人： 陈陈陈
 * 功能描述：
 *
 *   base_url_uaa
 */
object LoginApi : BaseNetworkApi<ILoginService>(Constants.base_url_login) {
    /**
     * 密码登录
     */
    suspend fun login(param: LoginParams) = getResult {
        service.login(param.toApiBody())
    }
    /**
     * 验证码登录
     */
    suspend fun loginSmscode(param: SmsCodeLoginParams) = getResult {
        service.loginSmscode(param.toApiBody())
    }
    /**
     * 发送验证码
     */
    suspend fun smsCode(mobile: String) = getResult {
        service.smsCode(mobile)
    }

    /**
     * 重置密码
     */
    suspend fun resetPassword(param: ResetPasswordParams) = getResult {
        service.resetPassword(param.toApiBody())
    }

    /**
     * 验证码校验
     */
    suspend fun checkSmsCode(phone:String,code:String) = getResult {
        service.checkSmsCode(phone,code)
    }
}