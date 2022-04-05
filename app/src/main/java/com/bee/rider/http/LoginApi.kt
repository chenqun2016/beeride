package com.bee.rider.http

import com.bee.rider.Constants
import com.bee.rider.params.LoginParams
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
object LoginApi : BaseNetworkApi<ILoginService>(Constants.base_url_uaa) {
    suspend fun login(param: LoginParams) = getResult {
        service.login(param.toApiBody())
    }

    suspend fun loginSmscode(param: SmsCodeLoginParams) = getResult {
        service.loginSmscode(param.toApiBody())
    }

    suspend fun smsCode(mobile: String) = getResult {
        service.smsCode(mobile)
    }
}